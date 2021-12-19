package tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


import com.example.databasedemo.entity.DBDUserOrderEntity;

public class EntityGenerator {
	
	
	 public static DBDUserOrderEntity randomGegerateAOrder() {
		DBDUserOrderEntity orderEntity = new DBDUserOrderEntity();
		
		Random r = new Random();
		orderEntity.setUserId(r.nextInt(100));
		orderEntity.setAddressId(r.nextInt(100));
		orderEntity.setOrderStatus("0");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		orderEntity.setCreateDate(formatter.format(new Date()));
		orderEntity.setUpdateDate(formatter.format(new Date()));
		orderEntity.setIsDeleted(0);		
		return orderEntity;
	}

}
