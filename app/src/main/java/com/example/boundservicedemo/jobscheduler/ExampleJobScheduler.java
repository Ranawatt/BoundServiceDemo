package com.example.boundservicedemo.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;

class ExampleJobScheduler extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
