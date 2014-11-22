package com.vivadaylight3.myrmecology.util;

public class Version {

    public String version;
    public int major, minor, patch;
    public char type;

    public Version(final String version) {
	this.version = version;
	final String[] parts = version.split(".");
	major = Integer.parseInt(parts[0]);
	minor = Integer.parseInt(parts[1]);
	type = parts[2].charAt(parts[2].length() - 1);
	parts[2] = parts[2].substring(0, parts[2].length() - 1);
	patch = Integer.parseInt(parts[2]);
    }

    @Override
    public String toString() {
	return major + "." + minor + "." + patch + ".a";
    }

}
