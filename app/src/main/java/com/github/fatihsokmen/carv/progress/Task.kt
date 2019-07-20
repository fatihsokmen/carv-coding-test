package com.github.fatihsokmen.carv.progress

class Task(
    private val taskInfo: TaskInfo
) {
    private val subTasks = mutableListOf<Task>()

    val hasSubTasks: Boolean
        get() = subTasks.isEmpty().not()

    val isSingleSubTask: Boolean
        get() = subTasks.isEmpty()


    var progress: Double = 0.0
        get() =
            if (subTasks.isEmpty()) { // Root task
                field
            } else {
                subTasks.sumByDouble { it.progress * it.taskInfo.weight }
                    .div(subTasks.sumByDouble { it.taskInfo.weight })
            }


    fun addSubTask(subTask: Task) {
        subTasks.add(subTask)
    }

    fun setSubTaskProgress(subTaskTag: String, progress: Double) {
        subTasks.find { it.taskInfo.tag == subTaskTag }?.progress = progress
    }
}

data class TaskInfo(
    val tag: String,
    val weight: Double = 1.0
)