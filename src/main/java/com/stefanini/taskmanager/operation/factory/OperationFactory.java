package com.stefanini.taskmanager.operation.factory;

import com.stefanini.taskmanager.operation.Operation;

public interface OperationFactory {

    Operation getOperation(String[] arg);
}
