package com.stefanini.taskmanager.operation.pivot;

import com.stefanini.taskmanager.operation.Operation;
import com.stefanini.taskmanager.service.impl.PivotServiceImpl;
import joptsimple.OptionParser;
import org.apache.logging.log4j.Logger;

public abstract class PivotOperation implements Operation {

    protected PivotServiceImpl relationService;
    protected Logger logger;
    protected String[] args;
    protected OptionParser parser;

    public PivotOperation(String[] args, OptionParser parser) {
        this.args = args;
        this.parser = parser;
    }
}
