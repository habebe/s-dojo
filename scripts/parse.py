import os
import json
import sys

key_descriptions = {'D':None,'F':None,'H':None}
key_mapping = {'E':'D','G':'F','I':'H'}
E_data = []
G_data = []
I_data = []
match = {"name":None,"date":None,"level":None,"region":None}
club = {"name":None,"uspsaCode":None}

def condition_name(name):
    name = name.replace(" ","_").lower()
    return name

def remove_uspsa_chars(value):
    return value.replace("-","").replace("_","")
    

def YesNo(value):
    value = value.lower()
    if value == "yes":
        return True
    elif value == "no":
        return False
    return value


def toFloat(value):
    try:
        value = float(value)
    except:
        value = 0
    return value

def toInt(value):
    try:
        value = int(value)
    except:
        value = 0
    return value

fields = {"E":
        {
            "match_points":[toFloat],
            "lastname":[],
            "division":[],
            "firstname":[],
            "comp":[],
            "class":[],
            "uspsa":[str.upper,remove_uspsa_chars],
            "power_factor":[],
            "female":[YesNo],
            "law":[YesNo],
            "miltary":[YesNo],
            "age":[]
        },
          "G":
          {
              "classifier_no": [], 
              "guntype": [], 
              "number": [], 
              "scoringtype": [], 
              "timesrun": [toInt], 
              "maximum_points": [toInt], 
              "stage_name": [],
              "minimum_rounds": [toInt], 
              "classifier": [YesNo]
              
          },
          "I":
          {
              "hit_factor": [toFloat], 
              "stage_power_factor": [], 
              "t3": [toFloat], 
              "total_penalty": [toFloat], 
              "miss": [toInt], 
              "dnf": [toInt], 
              "stage_points": [toFloat], 
              "no_shoot": [toInt], 
              "extra_shot": [toInt], 
              "total_points": [toFloat], 
              "double_poppers": [toInt], 
              "raw_points": [toFloat], 
              "dq": [YesNo], 
              "extra_hit": [toInt], 
              "stage_place": [toInt], 
              "comp": [], 
              "late_shot": [toInt], 
              "double_popper_miss": [toInt], 
              "stage": [], 
              "a": [toInt], 
              "c": [toInt], 
              "b": [toInt], 
              "d": [toInt], 
              "t4": [toFloat], 
              "t5": [toFloat], 
              "t2": [toFloat], 
              "gun": [], 
              "t1": [toFloat], 
              "no_penalty_miss": [toInt], 
              "time": [toFloat], 
              "additional_penalty": [toInt], 
              "procedural": [toInt]
          }
      }

def create_object(key,data,description):
    if(len(data) > len(description)):
        print "MISMATCH: "
        print "\nDESC:",description
        print "\nLINE:",data
        print "\nLEN:",len(data)," ",len(description)
        return None

    result = {}
    index = 0
    for i in data:
        field_name = condition_name(description[index])
        if key in fields:
            if field_name in fields[key]:
                transformations = fields[key][field_name]
                result[field_name] = i
                for k in transformations:
                    result[field_name] = k(result[field_name])
            pass
        else:
            result[field_name] = i
        index += 1
    return result

def parse(fileName):
    f = file(fileName,"r")
    data = f.read()
    data = data.replace("YesNo","Yes,No")
    lines = data.split("\n")
    for i in lines:
        key_index = i.find(" ")
        if key_index > 0:
            key = i[0:key_index]
            if key in key_descriptions:
                d = i[key_index+1:].split(",")
                m = {}
                index = 0
                for j in d:
                    m[index] = j 
                    index += 1
                key_descriptions[key] = m
           
            pass
        pass
    for i in lines:
        key_index = i.find(" ")
        if key_index >= 0:
            key = i[0:key_index]
            if key_index > 0:
                key = i[0:key_index]
                if key in key_mapping:
                    #print key ,"->",key_descriptions[key_mapping[key]]
                    d = i[key_index+1:].split(",")
                    o = create_object(key,d,key_descriptions[key_mapping[key]])
                    if o != None:
                        if key == "E":
                            E_data.append(o)
                        elif key == "G":
                            G_data.append(o)
                        elif key == "I":
                            comp = o["comp"]
                            stage = o["stage"]
                            del o["comp"]
                            del o["stage"]
                            o["compTransient"] = comp
                            o["stageTransient"] = stage
                            I_data.append(o)
                            pass
                        pass
                    pass
                elif key == "$INFO":
                    d = i[key_index+1:].split(":")
                    if len(d) > 0:
                        if d[0] == "Match name":
                            match["name"] = d[1].strip()
                        elif d[0] == "Match date":
                            match["date"] = d[1].strip()
                        elif d[0] == "Match Level":
                            match["level"] = d[1].strip()
                        elif d[0] == "Region":
                            match["region"] = d[1].strip()
                        elif d[0] == "Club Name":
                            club["name"] = d[1].strip()
                        elif d[0] == "Club Code":
                            club["uspsaCode"] = d[1].strip().upper()
                else:
                    print "'{0}'".format(key)
                    #print d
                    pass
            pass
        pass
    all_data = {
        "competitors":E_data,
        "stages":G_data,
        "scores":I_data,
        "match":match,
        "club":club
    }
    f = file(fileName+".json","w")
    print >> f, json.dumps(all_data,indent=4)    
    f.close()

    for field in fields:
        _f = fields[field]
        print "@Entity"
        if field == "E":
            print "public class Competitor{"
        elif field == "G":
            print "public class Stage{"
        elif field == "I":
            print "public class StageScore{"
        for f in _f:
            print '\t@JsonProperty("{0}")'.format(f)
            if _f[f] == [toInt]:
                print "\tprivate int " + f + ";\n"
            elif _f[f] == [toFloat]:
                print "\tprivate double " + f +";\n"
            elif _f[f] == [YesNo]:
                print "\tprivate boolean " + f +";\n"
            else:
                print "\tprivate String " + f + ";\n"
        print "}"
        print match
        print club
parse(sys.argv[1])
