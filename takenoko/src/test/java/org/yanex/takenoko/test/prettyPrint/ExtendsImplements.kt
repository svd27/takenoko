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
            property("e", KoType.BOOLEAN, OVERRIDE+VAL) {
                initializer(true)
            }
        }
    }

    @Test fun overrideFun() = testFile {
        function("some", OVERRIDE) {
            receiverType(KoType.BYTE)
            returnType(KoType.STRING)
            param("s", KoType.STRING, "abc")
            body(true) {
                append(
                        """|run {
                           |    val z = "xyz"
                           |    s + z
                           |}""".trimMargin())
            }
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

    @Test fun testCompanions() = testFile {

        classDeclaration("B") {
            companionDeclaration("") {
                property("n", KoType.INT, VAL) {
                    initializer(0)
                }
                extends(parseType("Tock<Int>"), 12)
            }
            companionDeclaration("ACompanion", INTERNAL) {
                extends(parseType("java.util.List<String>"))
            }
        }
    }

    @Test fun testComplexTypeArgs() = testFile {
        classDeclaration("Z") {
            extends(parseType("List<Map<String,Int>>"))
        }
        classDeclaration("W") {
            extends(parseType("List<List<*>>"))
        }
    }
}