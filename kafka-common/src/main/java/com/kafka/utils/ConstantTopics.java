package com.kafka.utils;

public class ConstantTopics {
	
	private ConstantTopics() {
		
	}
	
	public static final String HEADER_TYPE_ID = "__TypeId__";
    public static final String TYPE_ID_BASE_EVENT = "com.kafka.saga.BaseEventData";

	/*****************************************************
	   					TOPIC ORDER
	 *****************************************************/
	public static final String TOPIC_ORDERS = "topic-orders";
	public static final String GROUP_ORDER_1 = "group-order-1";
	
	/*****************************************************
						TOPIC INVOICES
	*****************************************************/
	public static final String TOPIC_INVOICES = "topic-invoices";
	public static final String GROUP_INVOICES_1 = "group-invoices-1";
}