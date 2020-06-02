package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.response.JobHistory;

public interface JobResponseMapper {
    JobHistory toServicePartResponse(Job job);
}
