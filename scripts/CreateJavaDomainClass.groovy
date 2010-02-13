/**
 * Gant script that creates a Java domain class
 * 
 * @author Marco Mannucci
 *
 * @since 0.4
 */

includeTargets << grailsScript("_GrailsInit")
includeTargets << grailsScript("_GrailsCreateArtifacts")

target ('default': "Creates a new domain class") {
    depends(checkVersion, parseArguments)

    promptForName(type: "Domain class")

    def name = argsMap["params"][0]
    createArtifact(name: name, suffix: "", type: 'JavaDomainClass', path: "src/java")
}
