package com.stefanini.taskmanager.tools.operations.impl.user;

import joptsimple.OptionParser;

public abstract class UserOperationWithArgs extends UserOperation {

    protected String[] args;
    protected OptionParser parser;

    UserOperationWithArgs(String[] args, OptionParser parser) {
        this.args = args;
        this.parser = parser;
    }
}
