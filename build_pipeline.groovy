buildPipelineView('DevOps_task-6') {
    filterBuildQueue()
    filterExecutors()
    title('DevOps_task_6 build Pipeline')
    displayedBuilds(1)
    selectedJob('seed_job')
    alwaysAllowManualTrigger()
    showPipelineParameters()
    refreshFrequency(60)
}
