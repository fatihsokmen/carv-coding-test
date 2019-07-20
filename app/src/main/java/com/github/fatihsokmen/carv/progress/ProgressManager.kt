package com.github.fatihsokmen.carv.progress

class ProgressManager {

    private var currentTask: Task? = null

    fun getProgress(): Double =
        currentTask?.progress ?: 0.0

    fun setProgress(progress: Double) {
        currentTask?.let {
            if (it.isSingleSubTask && progress in 0.0..100.0) {
                it.progress = progress
            }
        }
    }

    fun setSubTaskProgress(subTaskTag: String, progress: Double) {
        currentTask?.let {
            if (it.hasSubTasks && progress in 0.0..100.0) {
                it.setSubTaskProgress(subTaskTag, progress)
            }
        }
    }
}