package com.stefanini.taskmanager;

import com.stefanini.taskmanager.tools.operations.*;
import com.stefanini.taskmanager.tools.operations.factory.OperationFactory;


public class Application {

    public static void main(String[] args) {

        Operation operation = OperationFactory.getInstance().getOperation(args);

        if (operation != null) operation.execute();
    }
}
