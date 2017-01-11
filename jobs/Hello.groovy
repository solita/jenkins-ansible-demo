// This file is written in Job DSL.  See
// https://wiki.jenkins-ci.org/display/JENKINS/Job+DSL+Plugin for an overview
// and https://jenkinsci.github.io/job-dsl-plugin/ for a reference.
//
// This file must be directly in the jobs directory. If you move it to a
// subdirectory, it will be treated as a helper file and not a Job DSL script.

pipelineJob('hello') {
    definition {
        cps {
            script(readFileFromWorkspace('pipeline/Hello.groovy'))
            sandbox()
        }
    }
}
