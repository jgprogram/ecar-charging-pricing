package com.jgprogram.common.validation;

import com.jgprogram.common.exception.BusinessLogicException;

import java.lang.reflect.Constructor;

/**
 *
 * @author jgprogram
 */
public class BusinessAssertionConcern {

    private final AssertionConcern assertionConcern;

    public BusinessAssertionConcern() {
        assertionConcern = new AssertionConcern();
    }

    protected final <T extends BusinessLogicException> void assertArgumentEquals(Object anObject1, Object anObject2, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentEquals(anObject1, anObject2, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentEquals(Object anObject1, Object anObject2, String message) throws BusinessLogicException {
        assertArgumentEquals(anObject1, anObject2, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentFalse(boolean aBoolean, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentFalse(aBoolean, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentFalse(boolean aBoolean, String message) throws BusinessLogicException {
        assertArgumentFalse(aBoolean, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentLength(String aString, int aMaximum, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentLength(aString, aMaximum, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentLength(String aString, int aMaximum, String message) throws BusinessLogicException {
        assertArgumentLength(aString, aMaximum, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentLength(String aString, int aMinimum, int aMaximum, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentLength(aString, aMinimum, aMaximum, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentLength(String aString, int aMinimum, int aMaximum, String message) throws BusinessLogicException {
        assertArgumentLength(aString, aMinimum, aMaximum, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentMatchesToPattern(String aString, String aPattern, String aMessage, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentMatchesToPattern(aString, aPattern, aMessage);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentMatchesToPattern(String aString, String aPattern, String aMessage) throws BusinessLogicException {
        assertArgumentMatchesToPattern(aString, aPattern, aMessage, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentNotEmpty(String aString, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentNotEmpty(aString, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentNotEmpty(String aString, String message) throws BusinessLogicException {
        assertArgumentNotEmpty(aString, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentNotEquals(Object anObject1, Object anObject2, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentNotEquals(anObject1, anObject2, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentNotEquals(Object anObject1, Object anObject2, String message) throws BusinessLogicException {
        assertArgumentNotEquals(anObject1, anObject2, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentNotNull(Object anObject, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentNotNull(anObject, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentNotNull(Object anObject, String message) throws BusinessLogicException {
        assertArgumentNotNull(anObject, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentRange(double aValue, double aMinimum, double aMaximum, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentRange(aValue, aMinimum, aMaximum, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentRange(double aValue, double aMinimum, double aMaximum, String message) throws BusinessLogicException {
        assertArgumentNotNull(aValue, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentRange(float aValue, float aMinimum, float aMaximum, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentRange(aValue, aMinimum, aMaximum, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentRange(float aValue, float aMinimum, float aMaximum, String message) throws BusinessLogicException {
        assertArgumentRange(aValue, aMinimum, aMaximum, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentRange(int aValue, int aMinimum, int aMaximum, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentRange(aValue, aMinimum, aMaximum, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentRange(int aValue, int aMinimum, int aMaximum, String message) throws BusinessLogicException {
        assertArgumentRange(aValue, aMinimum, aMaximum, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentRange(long aValue, long aMinimum, long aMaximum, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentRange(aValue, aMinimum, aMaximum, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentRange(long aValue, long aMinimum, long aMaximum, String message) throws BusinessLogicException {
        assertArgumentRange(aValue, aMinimum, aMaximum, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertArgumentTrue(boolean aBoolean, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertArgumentTrue(aBoolean, message);
        } catch (IllegalArgumentException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final void assertArgumentTrue(boolean aBoolean, String message) throws BusinessLogicException {
        assertArgumentTrue(aBoolean, message, BusinessLogicException.class);
    }

    protected final <T extends BusinessLogicException> void assertStateFalse(boolean aBoolean, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertStateFalse(aBoolean, message);
        } catch (IllegalStateException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    protected final <T extends BusinessLogicException> void assertStateTrue(boolean aBoolean, String message, Class<T> exceptionClass) throws T {
        try {
            assertionConcern.assertStateTrue(aBoolean, message);
        } catch (IllegalStateException ex) {
            throw createBusinessException(exceptionClass, ex);
        }
    }

    private <T extends BusinessLogicException> T createBusinessException(Class<T> exceptionClass, Throwable throwable) {
        try {
            Constructor constructor = exceptionClass.getConstructor(String.class, Throwable.class);
            return (T) constructor.newInstance(throwable.getMessage(), throwable);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private <T extends BusinessLogicException> T createBusinessException(Class<T> exceptionClass, String message) {
        try {
            Constructor constructor = exceptionClass.getConstructor(String.class);
            return (T) constructor.newInstance(message);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
