package com.appauthority.appwiz.interfaces;

import com.appsauthority.appwiz.models.OrderDetailResponseObject;
import com.appsauthority.appwiz.models.OrderHistoryResponseObject;

public interface OrderHistoryCaller {

	public void orderDetailDownloaded(OrderDetailResponseObject orderDetailObj);
}
