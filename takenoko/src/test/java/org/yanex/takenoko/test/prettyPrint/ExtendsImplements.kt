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

    @Test fun overrideProperty() = testFile {
        classDeclaration("C") {
            primaryConstructor {
                param("c", KoType.DOUBLE, OVERRIDE)
                property("d", KoType.DOUBLE.nullable, VAL)
            }
        }
    }

    @Test fun overrideFun() = testFile {
        function("some", OVERRIDE) {
            receiverType(KoType.BYTE)
            returnType(KoType.CHAR)
        }


        classDeclaration("C") {
            primaryConstructor {
                param("c", KoType.DOUBLE, OVERRIDE)
                property("d", KoType.DOUBLE.nullable, VAL)
            }

            function("other", OVERRIDE + INLINE) {
                param("x", KoType.DOUBLE, OVERRIDE)
                returnType(KoType.CHAR.nullable)
            }
        }
    }
}