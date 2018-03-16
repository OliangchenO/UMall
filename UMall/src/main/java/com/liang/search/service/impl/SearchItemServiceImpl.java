package com.liang.search.service.impl;

import com.liang.common.exception.UMallException;
import com.liang.common.pojo.SearchItem;
import com.liang.search.mapper.ItemMapper;
import com.liang.search.service.SearchItemService;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author Liang
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

	private final static Logger log= LoggerFactory.getLogger(SearchItemServiceImpl.class);

	@Autowired
	private ItemMapper itemMapper;

	@Value("${ES_CONNECT_IP}")
	private String ES_CONNECT_IP;

	@Override
	public int importAllItems() {

		try{
			Settings settings = Settings.builder()
					.put("cluster.name", "UMall").build();
			TransportClient client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ES_CONNECT_IP), 9300));

			//批量添加
			BulkRequestBuilder bulkRequest = client.prepareBulk();

			//查询商品列表
			List<SearchItem> itemList = itemMapper.getItemList();

			//遍历商品列表
			for (SearchItem searchItem : itemList) {
				String image=searchItem.getProductImageBig();
				if (image != null && !"".equals(image)) {
					String[] strings = image.split(",");
					image=strings[0];
				}else{
					image="";
				}
				searchItem.setProductImageBig(image);
				bulkRequest.add(client.prepareIndex("item", "itemList", String.valueOf(searchItem.getProductId()))
						.setSource(jsonBuilder()
								.startObject()
								.field("productId", searchItem.getProductId())
								.field("salePrice", searchItem.getSalePrice())
								.field("productName", searchItem.getProductName())
								.field("sub_title", searchItem.getSub_title())
								.field("productImageBig", searchItem.getProductImageBig())
								.field("category_name", searchItem.getCategory_name())
								.endObject()
						)
				);
			}

			BulkResponse bulkResponse = bulkRequest.get();

			log.info("更新索引成功");

			client.close();
		}catch (Exception e){
			e.printStackTrace();
			throw new UMallException("导入ES索引库出错");
		}

		return 1;
	}
}
