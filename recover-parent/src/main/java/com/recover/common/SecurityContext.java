package com.recover.common;



/**
* Associates a given {@link SecurityContext} with the current execution thread.
* <p>
* This class provides a series of static methods that delegate to an instance of
* {@link org.springframework.security.core.context.SecurityContextHolderStrategy}. The
* purpose of the class is to provide a convenient way to specify the strategy that should
* be used for a given JVM. This is a JVM-wide setting, since everything in this class is
* <code>static</code> to facilitate ease of use in calling code.
* <p>
* To specify which strategy should be used, you must provide a mode setting. A mode
* setting is one of the three valid <code>MODE_</code> settings defined as
* <code>static final</code> fields, or a fully qualified classname to a concrete
* implementation of
* {@link org.springframework.security.core.context.SecurityContextHolderStrategy} that
* provides a public no-argument constructor.
* <p>
* There are two ways to specify the desired strategy mode <code>String</code>. The first
* is to specify it via the system property keyed on {@link #SYSTEM_PROPERTY}. The second
* is to call {@link #setStrategyName(String)} before using the class. If neither approach
* is used, the class will default to using {@link #MODE_THREADLOCAL}, which is backwards
* compatible, has fewer JVM incompatibilities and is appropriate on servers (whereas
* {@link #MODE_GLOBAL} is definitely inappropriate for server use).
*
* @author Ben Alex
*
*/
public class SecurityContext {
	
	private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<Authentication>();

	static {
		initialize();
	}

	// ~ Methods
	// ========================================================================================================

	/**
	 * Explicitly clears the context value from the current thread.
	 */
	public static void clearContext() {
		contextHolder.remove();
	}

	private static void initialize() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Obtain the current <code>SecurityContext</code>.
	 *
	 * @return the security context (never <code>null</code>)
	 */
	public static Authentication getContext() {
		return contextHolder.get();
	}


	/**
	 * Associates a new <code>SecurityContext</code> with the current thread of execution.
	 *
	 * @param context the new <code>SecurityContext</code> (may not be <code>null</code>)
	 */
	public static void setContext(Authentication context) {
		contextHolder.set(context);
	}
}

