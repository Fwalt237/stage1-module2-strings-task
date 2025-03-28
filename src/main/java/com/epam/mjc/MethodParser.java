package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        try {
            StringTokenizer firstToken = new StringTokenizer(signatureString, " ");
            List<String> methodSignatureList = new ArrayList<>();
            while (firstToken.hasMoreTokens()) {
                methodSignatureList.add(firstToken.nextToken());
            }
            if (methodSignatureList.size() < 3) {
                String returnType = methodSignatureList.get(0);
                String methodNameWithArguments = methodSignatureList.get(1);
                return getMethodSignatureWithoutAccessModifier(methodNameWithArguments, returnType);

            }
            String accessModifier = methodSignatureList.get(0);
            String returnType = methodSignatureList.get(1);
            String methodNameWithArguments = methodSignatureList.get(2);

            return getMethodSignature(methodNameWithArguments, accessModifier, returnType);
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("You should implement this method.");
        }
    }

    private static MethodSignature getMethodSignatureWithoutAccessModifier(String methodNameWithArguments, String returnType) {
        MethodSignature methodSignature =  splitMethodNameAndArgument(methodNameWithArguments);
        methodSignature.setReturnType(returnType);
        return methodSignature;
    }


    private static MethodSignature getMethodSignature(String methodNameWithArguments, String accessModifier, String returnType) {
        MethodSignature methodSignature =splitMethodNameAndArgument(methodNameWithArguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);
        return methodSignature;
    }

    private static MethodSignature splitMethodNameAndArgument(String methodNameWithArguments) {
        StringTokenizer secondToken = new StringTokenizer(methodNameWithArguments, "(,)");
        String methodName = secondToken.nextToken();
        List<String> argumentList = new ArrayList<>();
        while (secondToken.hasMoreTokens()) {
            argumentList.add(secondToken.nextToken());
        }

        List<MethodSignature.Argument> arguments = new ArrayList<>();
        for (String argument : argumentList) {
            String[] parts = argument.split(" ");
            if (parts.length == 1) {
                return new MethodSignature(methodName);
            }
            MethodSignature.Argument eachArgument = new MethodSignature.Argument(parts[0], parts[1]);
            arguments.add(eachArgument);
        }

        return new MethodSignature(methodName, arguments);
    }
}
