package com.neva.gradle.osgi.instance

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin

open class InstancePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.run { configure() }
    }

    private fun Project.configure() {
        logger.info("Applying instance plugin")

        plugins.apply(BasePlugin::class.java)
        tasks.create(CreateTask.NAME, CreateTask::class.java)
    }

}