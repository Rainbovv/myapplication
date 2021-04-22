package com.stefanini.taskmanager.operation.relation;

import com.stefanini.taskmanager.operation.Operation;
import com.stefanini.taskmanager.service.impl.UserTaskServiceImpl;
import joptsimple.OptionParser;
import org.apache.logging.log4j.Logger;

public abstract class UserTaskOperation implements Operation {

    protected UserTaskServiceImpl relationService;
    protected Logger logger;
    protected String[] args;
    protected OptionParser parser;

    public UserTaskOperation(String[] args, OptionParser parser) {
        this.args = args;
        this.parser = parser;
    }
}
