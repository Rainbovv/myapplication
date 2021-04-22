package com.stefanini.taskmanager.operation.user;

import joptsimple.OptionParser;

public abstract class UserOperationWithArgs extends UserOperation {

    protected String[] args;
    protected OptionParser parser;

    UserOperationWithArgs(String[] args, OptionParser parser) {
        this.args = args;
        this.parser = parser;
    }
}
