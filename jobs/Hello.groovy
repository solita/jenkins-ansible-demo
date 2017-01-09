pipelineJob('hello') {
    definition {
        cps {
            script(readFileFromWorkspace('pipeline/Hello.groovy'))
            sandbox()
        }
    }
}
