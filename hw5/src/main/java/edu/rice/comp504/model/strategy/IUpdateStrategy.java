package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

public interface IUpdateStrategy {
    public String getName();
    public void updateState(APaintObject context);
}
