package org.hl7.fhir.utilities.tests;

import org.hl7.fhir.utilities.npm.CachingPackageClient;
import org.hl7.fhir.utilities.npm.PackageClient.PackageInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class CachingPackageClientTests {

  @Test
  public void testExists() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages.fhir.org");
    Assertions.assertTrue(client.exists("hl7.fhir.r4.core", "4.0.1"));
    Assertions.assertTrue(!client.exists("hl7.fhir.r4.core", "1.0.2"));
    Assertions.assertTrue(!client.exists("hl7.fhir.nothing", "1.0.1"));
  }

  @Test
  public void testSearch() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages.fhir.org");
    List<PackageInfo> matches = client.search("core", null, null, false);
    for (PackageInfo pi : matches) {
      System.out.println(pi.toString());
    }
    Assertions.assertTrue(matches.size() > 0);
  }

  @Test
  public void testSearchNoMatches() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages.fhir.org");
    List<PackageInfo> matches = client.search("corezxxx", null, null, false);
    Assertions.assertTrue(matches.size() == 0);
  }

  @Test
  public void testVersions() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages.fhir.org");
    List<PackageInfo> matches = client.getVersions("Simplifier.Core.STU3");
    for (PackageInfo pi : matches) {
      System.out.println(pi.toString());
    }
    Assertions.assertTrue(matches.size() > 0);
  }

  @Test
  public void testVersionsNone() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages.fhir.org");
    List<PackageInfo> matches = client.getVersions("Simplifier.Core.STU3X");
    Assertions.assertTrue(matches.size() == 0);
  }

  @Test
  public void testExists2() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages2.fhir.org/packages");
    Assertions.assertTrue(client.exists("hl7.fhir.r4.core", "4.0.1"));
    Assertions.assertTrue(!client.exists("hl7.fhir.r4.core", "1.0.2"));
    Assertions.assertTrue(!client.exists("hl7.fhir.nothing", "1.0.1"));
    Assertions.assertTrue(client.exists("hl7.fhir.pubpack", "0.0.7"));
  }

  @Test
  public void testSearch2() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages2.fhir.org/packages");
    List<PackageInfo> matches = client.search("core", null, null, false);
    for (PackageInfo pi : matches) {
      System.out.println(pi.toString());
    }
    Assertions.assertTrue(matches.size() > 0);
  }

  @Test
  public void testSearchNoMatches2() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages2.fhir.org/packages");
    List<PackageInfo> matches = client.search("corezxxx", null, null, false);
    Assertions.assertTrue(matches.size() == 0);
  }

  @Test
  public void testVersions2() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages2.fhir.org/packages");
    List<PackageInfo> matches = client.getVersions("Simplifier.Core.STU3");
    for (PackageInfo pi : matches) {
      System.out.println(pi.toString());
    }
    Assertions.assertTrue(matches.size() == 0);
  }

  @Test
  public void testVersions2A() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages2.fhir.org/packages");
    List<PackageInfo> matches = client.getVersions("hl7.fhir.us.core");
    for (PackageInfo pi : matches) {
      System.out.println(pi.toString());
    }
    Assertions.assertTrue(matches.size() > 0);
  }

  @Test
  public void testVersionsNone2() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages2.fhir.org/packages");
    List<PackageInfo> matches = client.getVersions("Simplifier.Core.STU3X");
    Assertions.assertTrue(matches.size() == 0);
  }
  
  @Test
  public void testRegistry() throws IOException {
    CachingPackageClient client = new CachingPackageClient("http://packages.fhir.org/packages");
    List<PackageInfo> matches1 = client.listFromRegistry(null, null, null);
    List<PackageInfo> matches2 = client.listFromRegistry(null, null, "4.0.1");
    Assertions.assertTrue(matches1.size() > matches2.size());
  }
}