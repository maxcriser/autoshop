package com.example.maksim_zakharenka.autoshop.executable;

import com.example.maksim_zakharenka.autoshop.model.OrderModel;

import java.util.List;

public class MyOrdersExecutable {

    public MyOrdersExecutable() {

    }

    public List<OrderModel> execute() {
        return new OrderBySavedUsernameExecutable().execute();
    }
}