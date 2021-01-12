# jenkins_shared
This Jenkins shared library is used run pipelines from a shared location. 

This pipeline had following stages:
1. Checking out from git.
2. Maven Compile
3. Maven Build
4. Maven Deploy

## Calling Method

```shell script

@Library('jenkins-library@main') _
myPipeline(buildNumber:"${BUILD_NUMBER}")

```
