package com.ai.slp.sdk.components.sequence.dao;

import java.util.List;

import com.ai.slp.sdk.components.sequence.mo.Sequence;
import com.ai.slp.sdk.components.sequence.mo.SequenceCache;

public interface ISequenceDao {

    SequenceCache getSequenceCache(String sequenceName);

    List<Sequence> queryAllSequence();

    Sequence querySequenceByName(String sequenceName);

    void modifySequence(String sequenceName, long nextVal);

}
