package com.appsauthority.appwiz;

import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appsauthority.appwiz.adapters.OrderDetailAdapter;
import com.appsauthority.appwiz.custom.BaseActivity;
import com.appsauthority.appwiz.models.OrderObject;
import com.appsauthority.appwiz.models.Product;
import com.appsauthority.appwiz.utils.Constants;
import com.appsauthority.appwiz.utils.Helper;
import com.appsauthority.appwiz.utils.ImageCacheLoader;
import com.offpeaksale.seller.R;

public class OrderDetailActivity extends BaseActivity {
	public static OrderObject orderObj;
	RelativeLayout headerView;
	ImageView imageBack;
	TextView textViewHeader;
//	ListView itemListView;
	OrderDetailAdapter adapter;
	MapLayout mapLayout;
	private ImageCacheLoader imageCacheLoader;
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.order_detail_layout);
		Intent intent = getIntent();
		imageCacheLoader = new ImageCacheLoader(this);
		orderObj = (OrderObject) intent.getSerializableExtra("orderObj");
		headerView = (RelativeLayout) findViewById(R.id.headerView);
		imageBack = (ImageView) findViewById(R.id.imageBack);
		textViewHeader = (TextView) findViewById(R.id.textViewHeader);
		//tvQRCode.setText(orderObj.qrCode);
		
		textViewHeader.setText("Valid Voucher");
		//itemListView = (ListView) findViewById(R.id.lv_items);
	//	adapter = new OrderDetailAdapter(this, R.layout.row_order_item,
	//			orderObj.products);
	//	itemListView.setAdapter(adapter);

		imageBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	/*	tvQRCode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, ViewQRCodeActivity.class);
				intent.putExtra("couponCode", orderObj.qrCode);
				startActivity(intent);
			}
		});*/

		try {
			textViewHeader.setTypeface(Helper.getSharedHelper().boldFont);
			textViewHeader
					.setTextColor(Color.parseColor("#"
							+ Helper.getSharedHelper().reatiler
									.getRetailerTextColor()));
			//tvQRCode.setTextColor(Color.parseColor("#"
				//	+ Helper.getSharedHelper().reatiler.getRetailerTextColor()));
			headerView.setBackgroundColor(Color.parseColor("#"
					+ Helper.getSharedHelper().reatiler.getHeaderColor()));
			if (Helper.getSharedHelper().reatiler.appIconColor != null
					&& Helper.getSharedHelper().reatiler.appIconColor
							.equalsIgnoreCase("black")) {
				imageBack.setBackgroundResource(R.drawable.backbutton_black);

			} else {
				imageBack.setBackgroundResource(R.drawable.backbutton);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		initializeOrderInfo();
		/*if (orderObj.shippingStatus.equalsIgnoreCase("Fulfilled")) {
			tvQRCode.setVisibility(View.GONE);
		}*/
	}

	void initializeOrderInfo() {
		
	//	setMapFooter();

		TextView tvOrderId = (TextView)findViewById(R.id.tvOrderId);
		TextView tvOrderResturantName = (TextView) findViewById(R.id.tvOrderResturantName);
//		tvOrderResturantName.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(context, OutletMapActivity.class);
//				intent.putExtra("resturant_address", orderObj.products.get(0).getName().trim());
//				startActivity(intent);
//				
//			}
//		});
		TextView tvOrderResturantAddress = (TextView) findViewById(R.id.tvOrderResturantAddress);
		TextView tvOrderTelephone=(TextView) findViewById(R.id.tvOrderTelephone);
		TextView tvOrderDistance=(TextView) findViewById(R.id.tvOrderDistance);
		TextView	tvOrderStatus = (TextView) findViewById(R.id.tvOrderStatus);
		TextView tvOrderExpiry = (TextView) findViewById(R.id.tvOrderExpiry);
		
		
		
		ImageView iv_eshop=(ImageView)findViewById(R.id.iv_eshop);
		TextView tv_eshop_ResturantName=(TextView)findViewById(R.id.tv_eshop_ResturantName);
		TextView tvOldPriceDetails=(TextView)findViewById(R.id.tvOldPriceDetails);
		TextView tvNewPriceDetails=(TextView)findViewById(R.id.tvNewPriceDetails);
		TextView tvOrderDiscount=(TextView)findViewById(R.id.tvOrderDiscount);
		//LinearLayout MapLinear=(LinearLayout)findViewById(R.id.MapLinear);
	//	MapLinear.addView(mapLayout);


		try {
			tvOrderId.setTypeface(Helper.getSharedHelper().normalFont);
			tvOrderResturantName.setTypeface(Helper.getSharedHelper().normalFont);
			tvOrderResturantAddress.setTypeface(Helper.getSharedHelper().normalFont);
			tvOrderTelephone.setTypeface(Helper.getSharedHelper().normalFont);
			tvOrderDistance.setTypeface(Helper.getSharedHelper().normalFont);
			tvOrderStatus.setTypeface(Helper.getSharedHelper().normalFont);
			tvOrderExpiry.setTypeface(Helper.getSharedHelper().normalFont);
			
			tv_eshop_ResturantName.setTypeface(Helper.getSharedHelper().boldFont);
			tvOldPriceDetails.setTypeface(Helper.getSharedHelper().normalFont);
			tvNewPriceDetails.setTypeface(Helper.getSharedHelper().normalFont);
			tvOrderDiscount.setTypeface(Helper.getSharedHelper().normalFont);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String orderId="Order "+orderObj.qrCode;
		tvOrderId.setText(orderId);
		String resturantName="<b>Restaurant Name</b> " + orderObj.products.get(0).getName().trim(); 
		tvOrderResturantName.setText(Html.fromHtml(resturantName));
		
		if (orderObj.outletAddr != null) {
			String resturantAddress="<b>Address</b> " +orderObj.outletAddr;
			tvOrderResturantAddress.setText(Html.fromHtml(resturantAddress));
		}else
		{
			tvOrderResturantAddress.setVisibility(View.GONE);
		}
		if (orderObj.outletContact != null) {
			String resturantTelephone="<b>Telephone</b> " +orderObj.outletContact;
			tvOrderTelephone.setText(Html.fromHtml(resturantTelephone));
			Linkify.addLinks((tvOrderTelephone),Linkify.ALL);
		}
		else
		{
			tvOrderTelephone.setVisibility(View.GONE);
		}
		
		if (orderObj.outletLat != null && orderObj.outletLong != null) {
			String resturantDistance="<b>Distance</b> " +
		Helper.getSharedHelper().getDistanceBetween(Constants.TARGET_LAT,
					Constants.TARGET_LAT,
					Double.parseDouble(orderObj.outletLat),
					Double.parseDouble(orderObj.outletLong));
			tvOrderDistance.setText(Html.fromHtml(resturantDistance));
		}else
		{
			tvOrderDistance.setVisibility(View.GONE);
		}
		
		String status="<b>Status </b> " +orderObj.shippingStatus;
		tvOrderStatus.setText(Html.fromHtml(status));
		
		
		float conversionValue=0.0f;
		String selectedCurrencyCode;
		selectedCurrencyCode = Helper.getSharedHelper().currency_symbol;
		  if (conversionValue == 0) {
		   conversionValue = 1.0f;
		   selectedCurrencyCode = Helper.getSharedHelper().reatiler.defaultCurrency;
		  }
	
		//tvOrderTotal.setText("Total "+Helper.getSharedHelper() .getCurrencySymbol(selectedCurrencyCode)+orderObj.orderTotal);
		String comp[] = orderObj.orderDate.split(" ");

		String date = "";
		if (comp.length > 0) {
			date = comp[0];
		}
		
		if(orderObj.shippingStatus.equals("Expired"))
		{
			String expiry="<b>Expired On   </b> " +date;
			tvOrderExpiry.setText(Html.fromHtml(expiry));
		}else if(orderObj.shippingStatus.equals("Redeemed"))
		{
			String expiry="<b>Redeemed On </b> " +date;
			tvOrderExpiry.setText(Html.fromHtml(expiry));
		}else
		{
			String expiry="<b>Expiry  </b> " +date;
			tvOrderExpiry.setText(Html.fromHtml(expiry));
		}
		
		
		/*if (orderObj.orderInstr != null) {
			tvOrderInstruction.setText("Instruction "+orderObj.orderInstr);
		//	tvOrderInstruction.setVisibility(View.VISIBLE);
		} else {
			tvOrderInstruction.setVisibility(View.GONE);
		}*/
		
		imageCacheLoader.displayImage(orderObj.products.get(0).getImage(),R.drawable.image_placeholder, iv_eshop);
		tv_eshop_ResturantName.setText(orderObj.products.get(0).getName().trim());
		tvOldPriceDetails.setText(Helper.getSharedHelper() .getCurrencySymbol(selectedCurrencyCode)+orderObj.products.get(0).oldPrice);
		tvOldPriceDetails.setPaintFlags(tvOldPriceDetails.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
		tvNewPriceDetails.setText(Helper.getSharedHelper() .getCurrencySymbol(selectedCurrencyCode)+orderObj.products.get(0).newPrice);
		if(!orderObj.discountAmt.startsWith("0"))
		{
			String discount="<b>Discount </b> " +Helper.getSharedHelper() .getCurrencySymbol(selectedCurrencyCode)+orderObj.discountAmt;
			tvOrderDiscount.setText(Html.fromHtml(discount));
		}else
		{
			tvOrderDiscount.setVisibility(View.GONE);
		}
	}
	
	private void setMapFooter()
	{
		List<Product> products=orderObj.products;
		if(products!=null)
		{
			Product product = products.get(0);
			mapLayout = new MapLayout(this, this, product.outlets);
			mapLayout.init();
		}
	}
}