/**
 * Gant script that creates a new Grails controller
 * 
 * @author Marco Mannucci
 *
 * @since 0.4
 */

includeTargets << grailsScript("_GrailsInit")
includeTargets << grailsScript("_GrailsCreateArtifacts")

target ('default': "Creates a new controller") {
    depends(checkVersion, parseArguments)

    def type = "JavaController"
    promptForName(type: type)

    def name = argsMap["params"][0]
	createArtifact(name: name, suffix: "Controller", type: type, path: "src/java")

    def viewsDir = "${basedir}/grails-app/views/${propertyName}"
    ant.mkdir(dir:viewsDir)
	event("CreatedFile", [viewsDir])
}
