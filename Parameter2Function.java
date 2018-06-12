package org.mitre.synthea.export.cdwupload;

@FunctionalInterface
public interface Parameter2Function <P,Q,R>{
		public R apply (P p,  Q q);
}
