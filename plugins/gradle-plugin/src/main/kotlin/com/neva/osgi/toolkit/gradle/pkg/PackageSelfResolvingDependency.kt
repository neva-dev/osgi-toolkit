package com.neva.osgi.toolkit.gradle.pkg

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.FileCollectionDependency
import org.gradle.api.artifacts.component.ComponentIdentifier
import org.gradle.api.file.FileCollection
import org.gradle.api.internal.artifacts.dependencies.AbstractDependency
import org.gradle.api.internal.artifacts.dependencies.SelfResolvingDependencyInternal
import org.gradle.api.internal.tasks.DefaultTaskDependency
import org.gradle.api.tasks.TaskDependency
import java.io.File

class PackageSelfResolvingDependency(val source: FileCollection, val dependency: PackageDependency)
    : AbstractDependency(), FileCollectionDependency, SelfResolvingDependencyInternal {

    override fun getFiles(): FileCollection {
        return source
    }

    override fun resolve(): MutableSet<File> {
        return source.toMutableSet()
    }

    override fun resolve(transitive: Boolean): MutableSet<File> {
        return resolve()
    }

    override fun getGroup(): String? {
        return dependency.group
    }

    override fun getName(): String {
        return dependency.name
    }

    override fun getVersion(): String? {
        return dependency.version
    }

    override fun contentEquals(dependency: Dependency?): Boolean {
        if (dependency is PackageSelfResolvingDependency) {
            return source == dependency.source
        }

        return false
    }

    override fun copy(): Dependency {
        return PackageSelfResolvingDependency(source, dependency)
    }

    override fun getBuildDependencies(): TaskDependency {
        return DefaultTaskDependency()
    }

    override fun getTargetComponentId(): ComponentIdentifier? {
        return ComponentIdentifier { dependency.notation }
    }
}
