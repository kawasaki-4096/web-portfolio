package com.studio4096.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.List;

import javax.tools.DiagnosticCollector;
import javax.tools.DiagnosticListener;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

import com.studio4096.portfolio.dto.ClientInfoDto;
import com.studio4096.portfolio.entity.Page;

public class DynaBeanFactory {
	  public static void main(String[] args) throws Exception{
		DynaBeanFactory.create(Page.class);
		DynaBeanFactory.create(ClientInfoDto.class);
		
	}
	  public static <T> T create(Class<T> clazz) {
	    try {
			return getDynaClass(clazz).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	  }

	  @SuppressWarnings("unchecked")
	public static <T> Class<? extends T> getDynaClass(Class<T> originalClass){

	    String packageName = originalClass.getPackage().getName();
	    String simpleName = originalClass.getSimpleName() + "___DynaBean";
	    String fullName = packageName + "." + simpleName;
	    
	    StringBuilder src = new StringBuilder();
	    src.append("package " + packageName + ";\n" + "public class "
	        + simpleName + " extends " + originalClass.getName() + " {\n");

	    for (Field field : originalClass.getFields()) {
	      String fieldName = field.getName();
	      String capitalized = capitalize(fieldName);
	      String fieldType = field.getType().getName();
	      src.append("  public " + fieldType + " get" + capitalized + "() {\n"
	          + "    return this." + fieldName + ";\n"
	          + "  }\n"
	          + "  public void set" + capitalized + "(" + fieldType + " val) {\n"
	          + "    this." + fieldName + "=val;\n"
	          + "  }\n");
	    }

	    src.append("}\n");

	    System.out.println(src);
	    
	    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	    DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<JavaFileObject>();
	    JavaFileManager fileManager = new ClassFileManager(compiler, collector);

	    try {

	      List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
	      jfiles.add(new StringJavaFileObject(fullName, src.toString()));

	      JavaCompiler.CompilationTask task = compiler.getTask(null,
	          fileManager, collector, null, null, jfiles);

	      if (!task.call()) {
	        throw new IllegalStateException("Error!");
	      }

	      ClassLoader cl = fileManager.getClassLoader(null);
//	      ClassLoader cl = originalClass.getClassLoader();
			try {
				return (Class<? extends T>) cl.loadClass(fullName);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
	    } finally {
	      try {
			fileManager.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	    }
	  }

	  private static String capitalize(String name) {
	    return Character.toUpperCase(name.charAt(0))
	        + (name.length() > 1 ? name.substring(1) : "");
	  }

	  private static class StringJavaFileObject extends SimpleJavaFileObject {
	    private String content;

	    public StringJavaFileObject(String className, String content) {

	      super(URI.create("string:///" + className.replace('.', '/')
	          + Kind.SOURCE.extension), Kind.SOURCE);

	      this.content = content;
	    }

	    @Override
	    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
	      return content;
	    }
	  }

	  private static class JavaClassObject extends SimpleJavaFileObject {

	    public JavaClassObject(String name, Kind kind) {
	      super(URI.create("string:///" + name.replace('.', '/')
	          + kind.extension), kind);
	    }

	    protected final ByteArrayOutputStream bos = new ByteArrayOutputStream();

	    @Override
	    public OutputStream openOutputStream() throws IOException {
	      return bos;
	    }

	    public byte[] getBytes() {
	      return bos.toByteArray();
	    }

	    private Class<?> clazz = null;

	    public void setDefinedClass(Class<?> c) {
	      clazz = c;
	    }

	    public Class<?> getDefinedClass() {
	      return clazz;
	    }
	  }

	  private static class ClassFileManager extends
	      ForwardingJavaFileManager<JavaFileManager> {

	    public ClassFileManager(JavaCompiler compiler,
	        DiagnosticListener<? super JavaFileObject> listener) {
	      super(compiler.getStandardFileManager(listener, null, null));
	    }

	    private JavaClassObject jclassObject;

	    @Override
	    public JavaFileObject getJavaFileForOutput(Location location,
	        String className, Kind kind, FileObject sibling)
	        throws IOException {
	      jclassObject = new JavaClassObject(className, kind);
	      return jclassObject;
	    }

	    protected ClassLoader loader = null;

	    @Override
	    public ClassLoader getClassLoader(Location location) {
	      return new SecureClassLoader() {
	        @Override
	        protected Class<?> findClass(String name)
	            throws ClassNotFoundException {
	          Class<?> c = jclassObject.getDefinedClass();
	          if (c == null) {
	            byte[] b = jclassObject.getBytes();
	            c = super.defineClass(name, b, 0, b.length);
	            jclassObject.setDefinedClass(c);
	          }
	          return c;
	        }
	      };
	    }
	  }
	}
