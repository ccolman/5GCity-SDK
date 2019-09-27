package it.nextworks.composer.plugins.catalogue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import it.nextworks.nfvmano.libs.descriptors.templates.DescriptorTemplate;
import it.nextworks.nfvmano.libs.descriptors.templates.Node;
import it.nextworks.sdk.MonitoringParameter;
import it.nextworks.sdk.ServiceAction;
import it.nextworks.sdk.ServiceActionRule;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchiveBuilder {

    public static String createNSCSAR(DescriptorTemplate template, Set<MonitoringParameter> monitoringParameters,
                              Set<ServiceAction> actions, Set<ServiceActionRule> actionRules){

        String serviceName = "descriptor";
        String servicePackagePath;
        Map<String, Node> nodes = template.getTopologyTemplate().getNodeTemplates();
        for(Map.Entry<String, Node> node : nodes.entrySet()){
            if(node.getValue().getType().equals("tosca.nodes.nfv.NS"))
                serviceName = node.getKey();
        }

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        List<String> strings = new ArrayList<>();

        try{
            //Create directories
            File root = makeFolder(serviceName);
            File definitions = makeSubFolder(root, "Definitions");
            File files = makeSubFolder(root, "Files");
            File licenses = makeSubFolder(files, "Licences");
            File monitoring = makeSubFolder(files, "Monitoring");
            File scripts = makeSubFolder(files, "Scripts");
            File tests = makeSubFolder(files, "Tests");
            File metadata = makeSubFolder(root, "TOSCA-Metadata");

            //Create standard files
            File manifest = new File(root, serviceName + ".mf");
            strings.add("metadata:");
            strings.add("\tns_name: " + serviceName);
            strings.add("\tns_vendor_id: " + template.getMetadata().getVendor());
            strings.add("\tns_version: " + template.getMetadata().getVersion());
            strings.add(String.format("\tns_release_date_time: %1$TD %1$TT", ts));
            if(monitoringParameters.size() != 0) {
                strings.add("\nmonitoring:");
                strings.add("\tmain_monitoring_descriptor:");
                strings.add("\t\tSource: Files/Monitoring/monitoringParameters.json");
            }
            if(actions.size() != 0) {
                strings.add("\nactions:");
                strings.add("\tmain_actions_descriptor:");
                strings.add("\t\tSource: Files/Monitoring/actions.json");
            }
            if(actionRules.size() != 0) {
                strings.add("\naction_rules:");
                strings.add("\tmain_action_rules_descriptor:");
                strings.add("\t\tSource: Files/Monitoring/actionRules.json");
            }
            Files.write(manifest.toPath(), strings);
            strings.clear();
            File license = new File(licenses, "LICENSE");
            Files.write(license.toPath(), strings);
            File changeLog = new File(files, "ChangeLog.txt");
            strings.add(String.format("%1$TD %1$TT - New NS Package according to ETSI GS NFV-SOL004 v 2.5.1", ts));
            Files.write(changeLog.toPath(), strings);
            strings.clear();
            File certificate = new File(files, serviceName + ".cert");
            Files.write(certificate.toPath(), strings);
            File toscaMetadata = new File(metadata, "TOSCA.meta");
            strings.add("TOSCA-Meta-File-Version: 1.0");
            strings.add("CSAR-Version: 1.1");
            strings.add("CreatedBy: 5GCity-SDK");
            strings.add("Entry-Definitions: Definitions/"+ serviceName + ".yaml");
            Files.write(toscaMetadata.toPath(), strings);
            strings.clear();

            //Create descriptor files
            File descriptorFile = new File(definitions, serviceName + ".yaml");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            File monitoringParamsFile;
            File actionsFile;
            File actionRulesFile;
            if(monitoringParameters.size() != 0) {
                monitoringParamsFile = new File(monitoring, "monitoringParameters.json");
                mapper.writeValue(monitoringParamsFile, monitoringParameters);
            }
            if(actions.size() != 0) {
                actionsFile = new File(monitoring, "actions.json");
                mapper.writeValue(actionsFile, actions);
            }
            if(actionRules.size() != 0) {
                actionRulesFile = new File(monitoring, "actionRules.json");
                mapper.writeValue(actionRulesFile, actionRules);
            }
            mapper = new ObjectMapper(new YAMLFactory());
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.writeValue(descriptorFile, template);

            servicePackagePath = compress(root.toPath().toString());
        } catch (IOException e) {
            throw new IllegalStateException(
                String.format("Could not write files. Error: %s", e.getMessage(), e)
            );
        }

        return servicePackagePath;
    }

    public static String createVNFCSAR(DescriptorTemplate template, Set<MonitoringParameter> monitoringParameters, String cloudInit){

        String functionName = "descriptor";
        String functionPackagePath;
        Map<String, Node> nodes = template.getTopologyTemplate().getNodeTemplates();
        for(Map.Entry<String, Node> node : nodes.entrySet()){
            if(node.getValue().getType().equals("tosca.nodes.nfv.VNF"))
                functionName = node.getKey();
        }

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        List<String> strings = new ArrayList<>();

        try{
            //Create directories
            File root = makeFolder(functionName);
            File definitions = makeSubFolder(root, "Definitions");
            File files = makeSubFolder(root, "Files");
            File licenses = makeSubFolder(files, "Licences");
            File monitoring = makeSubFolder(files, "Monitoring");
            File scripts = makeSubFolder(files, "Scripts");
            File tests = makeSubFolder(files, "Tests");
            File metadata = makeSubFolder(root, "TOSCA-Metadata");

            //Create standard files
            File manifest = new File(root, functionName + ".mf");
            strings.add("metadata:");
            strings.add("\tvnf_product_name: " + functionName);
            strings.add("\tvnf_provider_id: " + template.getMetadata().getVendor());
            strings.add("\tvnf_package_version: " + template.getMetadata().getVersion());
            strings.add(String.format("\tvnf_release_date_time: %1$TD %1$TT", ts));
            if(monitoringParameters.size() != 0){
                strings.add("\nmonitoring:");
                strings.add("\tmain_monitoring_descriptor:");
                strings.add("\t\tSource: Files/Monitoring/monitoringParameters.json");
            }
            if(cloudInit != null) {
                strings.add("\nconfiguration:");
                strings.add("\tcloud_init:");
                strings.add("\t\tSource: Files/Scripts/cloud-init.txt");
            }
            Files.write(manifest.toPath(), strings);
            strings.clear();
            File license = new File(licenses, "LICENSE");
            Files.write(license.toPath(), strings);
            File changeLog = new File(files, "ChangeLog.txt");
            strings.add(String.format("%1$TD %1$TT - New VNF Package according to ETSI GS NFV-SOL004 v 2.5.1", ts));
            Files.write(changeLog.toPath(), strings);
            strings.clear();
            File certificate = new File(files, functionName + ".cert");
            Files.write(certificate.toPath(), strings);
            File toscaMetadata = new File(metadata, "TOSCA.meta");
            strings.add("TOSCA-Meta-File-Version: 1.0");
            strings.add("CSAR-Version: 1.1");
            strings.add("CreatedBy: 5GCity-SDK");
            strings.add("Entry-Definitions: Definitions/"+ functionName + ".yaml");
            Files.write(toscaMetadata.toPath(), strings);
            strings.clear();

            //Create descriptor files
            File descriptorFile = new File(definitions, functionName + ".yaml");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            File monitoringParamsFile;
            if(monitoringParameters.size() != 0) {
                monitoringParamsFile = new File(monitoring, "monitoringParameters.json");
                mapper.writeValue(monitoringParamsFile, monitoringParameters);
            }
            File cloudInitFile;
            if(cloudInit != null){
                cloudInitFile = new File(scripts, "cloud-init.txt");
                Files.write(cloudInitFile.toPath(), cloudInit.getBytes());
            }
            mapper = new ObjectMapper(new YAMLFactory());
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.writeValue(descriptorFile, template);

            functionPackagePath = compress(root.toPath().toString());
        } catch (IOException e) {
            throw new IllegalStateException(
                String.format("Could not write files. Error: %s", e.getMessage(), e)
            );
        }

        return functionPackagePath;
    }

    public static File makeFolder(String name) {
        File folder = new File("/tmp", name);
        if (folder.isDirectory()) {
            if (!rmRecursively(folder)) {
                throw new IllegalStateException(
                    String.format("Could not delete folder %s", folder.getAbsolutePath())
                );
            }
        }
        if (!folder.mkdir()) {
            throw new IllegalArgumentException(
                String.format("Cannot create folder %s", folder.getAbsolutePath())
            );
        }
        return folder;
    }

    public static boolean rmRecursively(File folder) {
        SimpleFileVisitor<Path> deleter = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e)
                throws IOException {
                if (e == null) {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                } else {
                    // directory iteration failed
                    throw e;
                }
            }
        };
        try {
            Files.walkFileTree(folder.toPath(), deleter);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static File makeSubFolder(File folder, String subFolder) {
        File newFolder = new File(folder, subFolder);
        if (!newFolder.mkdir()) {
            throw new IllegalArgumentException(
                String.format("Cannot create folder %s", newFolder.getAbsolutePath())
            );
        }
        return newFolder;
    }

    public static void copyFile(File fileFolder, File file) {
        try {
            Files.copy(file.toPath(), new File(fileFolder, file.getName()).toPath());
        } catch (IOException e) {
            String msg = String.format(
                "Cannot copy icon file %s to folder %s",
                file.getAbsolutePath(),
                fileFolder.getAbsolutePath()
            );
            throw new IllegalArgumentException(msg, e);
        }
    }

    /*
    public static void zip(File directory, File zipfile) throws IOException {
        URI base = directory.toURI();
        Deque<File> queue = new LinkedList<File>();
        queue.push(directory);
        OutputStream out = new FileOutputStream(zipfile);
        Closeable res = out;
        try {
            ZipOutputStream zout = new ZipOutputStream(out);
            res = zout;
            while (!queue.isEmpty()) {
                directory = queue.pop();
                for (File kid : directory.listFiles()) {
                    String name = base.relativize(kid.toURI()).getPath();
                    if (kid.isDirectory()) {
                        queue.push(kid);
                        name = name.endsWith("/") ? name : name + "/";
                        zout.putNextEntry(new ZipEntry(name));
                    } else {
                        zout.putNextEntry(new ZipEntry(name));
                        copy(kid, zout);
                        zout.closeEntry();
                    }
                }
            }
        } finally {
            res.close();
        }
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        while (true) {
            int readCount = in.read(buffer);
            if (readCount < 0) {
                break;
            }
            out.write(buffer, 0, readCount);
        }
    }

    private static void copy(File file, OutputStream out) throws IOException {
        InputStream in = new FileInputStream(file);
        try {
            copy(in, out);
        } finally {
            in.close();
        }
    }

    private static void copy(InputStream in, File file) throws IOException {
        OutputStream out = new FileOutputStream(file);
        try {
            copy(in, out);
        } finally {
            out.close();
        }
    }
    */
    public static String compress(String dirPath) {
        final Path sourceDir = Paths.get(dirPath);
        String zipFileName = dirPath.concat(".zip");
        try {
            final ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                    try {
                        Path targetFile = sourceDir.relativize(file);
                        outputStream.putNextEntry(new ZipEntry(targetFile.toString()));
                        byte[] bytes = Files.readAllBytes(file);
                        outputStream.write(bytes, 0, bytes.length);
                        outputStream.closeEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipFileName;
    }
}