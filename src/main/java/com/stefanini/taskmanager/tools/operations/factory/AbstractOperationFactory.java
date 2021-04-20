package com.stefanini.taskmanager.tools.operations.factory;

import com.stefanini.taskmanager.tools.operations.Operation;

public interface AbstractOperationFactory {

    Operation getOperation(String[] arg) throws Exception;
}
