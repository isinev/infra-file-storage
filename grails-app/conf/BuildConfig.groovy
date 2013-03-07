grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.repos.default = "quonb-snapshot"

grails.project.dependency.distribution = {
    String serverRoot = "http://mvn.quonb.org"
    remoteRepository(id: 'quonb-snapshot', url: serverRoot + '/plugins-snapshot-local/')
    remoteRepository(id: 'quonb-release', url: serverRoot + '/plugins-release-local/')
}

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
        // excludes 'hibernate'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        mavenLocal()

        mavenRepo "http://mvn.quonb.org/repo"
        grailsRepo "http://mvn.quonb.org/repo", "quonb"

        mavenRepo "http://www.jets3t.org/maven2"
        mavenCentral()
    }
    dependencies {
        compile "net.java.dev.jets3t:jets3t:0.9.0"

        test("org.spockframework:spock-grails-support:0.7-groovy-1.8") {
            export = false
        }
    }

    plugins {
        build(":tomcat:$grailsVersion",
                ":release:latest.release") {
            export = false
        }
        runtime(":hibernate:$grailsVersion") {
            export = false
        }

        //compile ":platform-core:latest.release"

        test(":spock:latest.release") {
            exclude "spock-grails-support"
            export = false
        }
    }
}
