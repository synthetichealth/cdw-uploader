package org.mitre.synthea.export.cdwupload;

//required for type checking

@FunctionalInterface
public interface Parameter2Function <P,Q,R>{
		public R apply (P p,  Q q);
}

