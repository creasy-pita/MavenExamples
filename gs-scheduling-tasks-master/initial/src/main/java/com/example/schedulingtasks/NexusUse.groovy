package com.example.schedulingtasks

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.sonatype.nexus.repository.storage.Asset
import org.sonatype.nexus.repository.storage.Component
import org.sonatype.nexus.repository.storage.Query
import org.sonatype.nexus.repository.storage.StorageFacet

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

def request = new JsonSlurper().parseText(args)
assert request.repoName: 'repoName parameter is required'

log.info("Gathering Asset list for repository: ${request.repoName} ")

def repo = repository.repositoryManager.get(request.repoName)
StorageFacet storageFacet = repo.facet(StorageFacet)
def tx = storageFacet.txSupplier().get()
def names = []
def versions = []
def creators = []
def urls = []
def lastUpdateds = []
try {
    tx.begin()
    String lastUpdateBeginDate = request.lastUpdateBeginDate;
    log.info("lastUpdateBeginDate: ${request.lastUpdateBeginDate} ")
    if( lastUpdateBeginDate == null || lastUpdateBeginDate =="")
    {
        def fmt = DateTimeFormat.forPattern('yyyy-MM-dd HH:mm:ss')
        lastUpdateBeginDate = new DateTime(1900,1,1).toString(fmt)
    }
//    Iterable<Component> components  = tx.findComponents(Query.builder().where('last_updated>=').param(lastUpdateBeginDatex`).build(), [repo])
//    componentsId = components.collect{"${it.entityMetadata.getId().getValue()}"}
//    Iterator<Component> componentIterator = components.iterator();
//    HashMap<String, Component> componentHashMap = new HashMap<>();
//    while(componentIterator.hasNext()){
//        Component com = componentIterator.next();
//        componentHashMap.put(com.entityMetadata.getId().getValue(),Component);
//    }
    Iterable<Asset> assets = tx.
            findAssets(Query.builder().where('last_updated >=').param(lastUpdateBeginDate).build(), [repo])
    Iterator<Asset> assetIterator = assets.iterator();
    List<Asset> assetList = new ArrayList<>();
    while(assetIterator.hasNext()){
        Asset asset = assetIterator.next();
        assetList.add(asset);
        String[] nameList = asset.name().split('/');

        if(nameList.length>1){
            String componentName = nameList[0]
            String version = nameList[2].replace(componentName+"-","").replace(".tgz","")//TBD 去除末尾的文件扩展名
            names.add(componentName)
            versions.add(version)
            creators.add(asset.createdBy())
            urls.add("/repository/${repo.name}/${asset.name()}")
            lastUpdateds.add("${asset.lastUpdated()}")
        }
    }

//    names = assets.collect{"${it.name()}"}
//    urls = assets.collect { "/repository/${repo.name}/${it.name()}" }
//    lastUpdateds = assets.collect{"${it.lastUpdated()}"}
    //infos = assets.collect{"name:${it.name()}|blobCreated:${it.blobCreated()}|lastUpdated:${it.lastUpdated()}|"}
}
finally {
    tx.close()
}

def result = JsonOutput.toJson([
        names: names,
        versions: versions,
        urls: urls,
        lastUpdateds: lastUpdateds
        ,
        creators:creators
])
return result


