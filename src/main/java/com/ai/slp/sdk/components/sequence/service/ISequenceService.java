package com.ai.slp.sdk.components.sequence.service;

public interface ISequenceService {

    Long nextValue(String sequenceName);

    void modifySequence(String sequenceName, long nextVal);

}
