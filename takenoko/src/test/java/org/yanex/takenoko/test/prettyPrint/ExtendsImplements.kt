package org.yanex.takenoko.test.prettyPrint

import org.junit.Test
import org.yanex.takenoko.*

class ExtendsImplements : AbstractPrettyPrintOutputTest() {
    override val prefix: String = "extend/"

    @Test fun implementWithType() = testFile {
        interfaceDeclaration("A") {
            typeParam("T")
        }

        classDeclaration("AImpl") {
            extends(KoType.Companion.parseType("java.util.ArrayList<String>"))
            implements(KoClassType("A", listOf(KoType.DOUBLE)))
        }
    }

    @Test fun nullablePar() = testFile {
        classDeclaration("B") {
            primaryConstructor {
                param("s", KoNullableType(KoType.STRING))
            }
        }
    }
}