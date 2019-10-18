{
  "_id": ObjectId("5da9588cd7708f001401ab45"),
  "createdAt": ISODate("2019-10-18T06:15:40.995+0000"),
  "updatedAt": ISODate("2019-10-18T06:24:54.111+0000"),
  "enquiryId": ObjectId("5da95880d7708f001401ab44"),
  "assemblyName": "98",
  "isCostPrice": true,
    "materialCost": 8875.008000000002,
  "processingCost": NumberInt(0),
  "addonCost": NumberInt(0),
  "totalWeight": 51.84,
  "materialCost": 8875.008000000002,
  "processingCost": NumberInt(0),
  "addonCost": NumberInt(0),
  "extrasCost": NumberInt(0),
  "totalCost": 13214.72304943419,
  "estimateCreatedUser": ObjectId("5a97b1c97e2636373f98f817"),
  "estimateUpdatedUser": ObjectId("5a97b1c97e2636373f98f817"),
  "version": NumberInt(1),
  "assemblyNumber": "AS5",
  "extras": [
    
  ],
  "addons": [
    {
      "rate": NumberInt(40),
      "showRateFields": true,
      "showQuantityFields": true,
      "quantity": {
        "supportingVariable": {
          "supportingVariable": "3",
          "value": NumberInt(1),
          "uom": "kg"
        },
        "keyValue": {
          "keyVariable": "SMA",
          "keyValue": NumberInt(80),
          "actualKeyValue": NumberInt(40),
          "uom": "kg"
        },
        "utilization": NumberInt(65),
        "contengncyOrWastage": NumberInt(10),
        "total": NumberInt(1)
      },
      "totalWeight": NumberInt(286),
      "totalCost": NumberInt(2288),
      "weightPerUnit": NumberInt(5),
      "addonType": {
        "_id": "5d935199926ab4002589a415",
        "createdAt": "2019-10-01T13:16:09.149Z",
        "updatedAt": "2019-10-04T09:25:16.986Z",
        "showQuantityFields": true,
        "showRateFields": true,
        "allowAtAssSubAss": true,
        "addonTypeName": "addon rateON&QtyON",
        "remarks": "rk",
        "materialCat": {
          "_id": "5d933268926ab4002589a3ac",
          "createdAt": "2019-10-01T11:03:04.022Z",
          "updatedAt": "2019-10-01T11:05:40.554Z",
          "materialCatName": "copper material",
          "materialCatIndex": NumberInt(1),
          "subCat": [
            {
              "_id": "5d9332a3926ab4002589a3ad",
              "createdAt": "2019-10-01T11:04:03.856Z",
              "updatedAt": "2019-10-01T11:04:29.182Z",
              "materialSubCatName": "copper material sub_cat",
              "catId": "5d933268926ab4002589a3ac",
              "subCatIndex": NumberInt(1),
              "materials": [
                "5d9332bd926ab4002589a3ae"
              ],
              "__v": NumberInt(0)
            },
            {
              "_id": "5d933304926ab4002589a3af",
              "createdAt": "2019-10-01T11:05:40.552Z",
              "updatedAt": "2019-10-01T11:12:24.936Z",
              "materialSubCatName": "copper material sub_cat2",
              "catId": "5d933268926ab4002589a3ac",
              "subCatIndex": NumberInt(2),
              "materials": [
                "5d93332e926ab4002589a3b0",
                "5d9333e2926ab4002589a3b1",
                "5d933493926ab4002589a3b2",
                "5d933498926ab4002589a3b3"
              ],
              "__v": NumberInt(0)
            }
          ],
          "__v": NumberInt(0)
        },
        "materialSubCat": {
          "_id": "5d9332a3926ab4002589a3ad",
          "createdAt": "2019-10-01T11:04:03.856Z",
          "updatedAt": "2019-10-01T11:04:29.182Z",
          "materialSubCatName": "copper material sub_cat",
          "catId": "5d933268926ab4002589a3ac",
          "subCatIndex": NumberInt(1),
          "materials": [
            "5d9332bd926ab4002589a3ae"
          ],
          "__v": NumberInt(0)
        },
        "addonIndex": NumberInt(1),
        "quantity": {
          "additionalInput": "3",
          "linkedKey": "SMA",
          "mulFact": "2",
          "percentageUse": NumberInt(65),
          "additionalInputUom": {
            "_id": "5a33a16e23996d036964ad4c",
            "createdAt": "2017-12-15T10:18:22.373Z",
            "updatedAt": "2017-12-15T10:18:22.373Z",
            "uomName": "kg",
            "__v": NumberInt(0)
          },
          "linkedKeyUom": {
            "_id": "5a33a16e23996d036964ad4c",
            "createdAt": "2017-12-15T10:18:22.373Z",
            "updatedAt": "2017-12-15T10:18:22.373Z",
            "uomName": "kg",
            "__v": NumberInt(0)
          },
          "finalUom": null
        },
        "rate": {
          "mulFact": "2",
          "uom": {
            "_id": "5a33a16e23996d036964ad4c",
            "createdAt": "2017-12-15T10:18:22.373Z",
            "updatedAt": "2017-12-15T10:18:22.373Z",
            "uomName": "kg",
            "__v": NumberInt(0)
          }
        },
        "__v": NumberInt(0)
      },
      "addonItem": {
        "_id": "5d9332bd926ab4002589a3ae",
        "createdAt": "2019-10-01T11:04:29.180Z",
        "updatedAt": "2019-10-04T09:22:32.715Z",
        "materialName": "coper",
        "density": NumberInt(10),
        "typicalRatePerKg": NumberInt(20),
        "weightPerUnit": NumberInt(5),
        "rollingIndex": NumberInt(1),
        "cuttingIndex": NumberInt(2),
        "bendingIndex": NumberInt(3),
        "fabrictionIndex": NumberInt(4),
        "efficiency": NumberInt(70),
        "materialSubCategory": {
          "_id": "5d9332a3926ab4002589a3ad",
          "createdAt": "2019-10-01T11:04:03.856Z",
          "updatedAt": "2019-10-01T11:04:29.182Z",
          "materialSubCatName": "copper material sub_cat",
          "catId": "5d933268926ab4002589a3ac",
          "subCatIndex": NumberInt(1),
          "__v": NumberInt(0),
          "materials": [
            "5d9332bd926ab4002589a3ae"
          ]
        },
        "matIndex": NumberInt(1),
        "__v": NumberInt(0),
        "type": "standard"
      },
      "addonNumber": "AS5AD1",
      "salesTotalCost": 2542.222222222222
    }
  ],
  "processing": [
    
  ],
  "subAssemblies": [
    {
      "subAssemblyName": "sub",
      "subAssemblyNumber": "AS5SA1",
      "quantity": NumberInt(1),
      "keyValueCalculations": {
        "perimeter": NumberInt(8),
        "sheetMetalArea": NumberInt(40),
        "surfaceArea": NumberInt(15),
        "grossWeight": 51.84,
        "netWeight": NumberInt(48)
      },
      "totalWeight": 51.84,
      "materialCost": 8294.400000000001,
      "processingCost": NumberInt(0),
      "addonCost": NumberInt(0),
      "extrasCost": NumberInt(0),
      "totalCost": 8294.400000000001,
      "subAssemblyParts": [
        {
          "partName": "part",
          "partNumber": "AS5SA1PT1",
          "partIcon": "",
          "shortcut": {
            "_id": "5d933939926ab4002589a3e1",
            "updatedAt": "2019-10-01T11:32:50.436Z",
            "thickness": "3",
            "length": "5",
            "wastage": "8",
            "formFactor": "4",
            "sizeFactor": "2",
            "presetName": "polygon rtNC",
            "size": "part_cat",
            "shape": {
              "_id": "5d933653926ab4002589a3b4",
              "createdAt": "2019-10-01T11:19:47.471Z",
              "updatedAt": "2019-10-17T10:30:17.287Z",
              "shapeName": "polygon rt",
              "namingConvention": "polygon rtN",
              "shapeIndex": NumberInt(1),
              "partFormulae": {
                "perimeter": "a*b",
                "sheetMetalArea": "c*d",
                "surfaceArea": "a+b+c+d/2",
                "netWeight": "(a*b)+(c*d)",
                "grossWeight": "((a*b)+(c*d))*((wtg+100)/100)"
              },
              "wastage": "8",
              "length": NumberInt(5),
              "thickness": NumberInt(3),
              "formFactor": "4",
              "sizeFactor": "2",
              "variable": [
                {
                  "_id": "5da842b9ed5e430118c59af2",
                  "varName": "a"
                },
                {
                  "_id": "5da842b9ed5e430118c59af1",
                  "varName": "b"
                },
                {
                  "_id": "5da842b9ed5e430118c59af0",
                  "varName": "c"
                },
                {
                  "_id": "5da842b9ed5e430118c59aef",
                  "varName": "d"
                },
                {
                  "_id": "5da842b9ed5e430118c59aee",
                  "varName": "e"
                }
              ],
              "icon": {
                "fileId": "1569928706824---Screenshot_from_2019-08-26_13-34-36.png",
                "fileName": "Screenshot_from_2019-08-26_13-34-36.png"
              },
              "type": "2d",
              "__v": NumberInt(0),
              "image": {
                "fileId": "1571308212544---Screenshot_from_2019-10-09_13-17-38.png",
                "fileName": "Screenshot_from_2019-10-09_13-17-38.png"
              }
            },
            "partType": {
              "_id": "5d93390a926ab4002589a3df",
              "createdAt": "2019-10-01T11:31:22.307Z",
              "updatedAt": "2019-10-01T11:32:42.600Z",
              "partTypeName": "Part Type - bolt",
              "partTypeCode": "Part code - bolt",
              "shape": "5d933653926ab4002589a3b4",
              "partTypeCat": "5d9338b8926ab4002589a3dd",
              "material": [
                {
                  "_id": "5d9332bd926ab4002589a3ae",
                  "createdAt": "2019-10-01T11:04:29.180Z",
                  "updatedAt": "2019-10-04T09:22:32.715Z",
                  "materialName": "coper",
                  "density": NumberInt(10),
                  "typicalRatePerKg": NumberInt(20),
                  "weightPerUnit": NumberInt(5),
                  "rollingIndex": NumberInt(1),
                  "cuttingIndex": NumberInt(2),
                  "bendingIndex": NumberInt(3),
                  "fabrictionIndex": NumberInt(4),
                  "efficiency": NumberInt(70),
                  "materialSubCategory": "5d9332a3926ab4002589a3ad",
                  "matIndex": NumberInt(1),
                  "type": "standard",
                  "__v": NumberInt(0)
                }
              ],
              "extras": [
                
              ],
              "addons": [
                
              ],
              "proccessing": [
                
              ],
              "__v": NumberInt(0)
            },
            "partFormulae": {
              "perimeter": "8",
              "sheetMetalArea": "40",
              "surfaceArea": "15",
              "grossWeight": "51.84",
              "netWeight": "48"
            },
            "variable": [
              {
                "varValue": NumberInt(2),
                "_id": "5d933653926ab4002589a3b9",
                "varName": "a"
              },
              {
                "varValue": NumberInt(4),
                "_id": "5d933653926ab4002589a3b8",
                "varName": "b"
              },
              {
                "varValue": NumberInt(5),
                "_id": "5d933653926ab4002589a3b7",
                "varName": "c"
              },
              {
                "varValue": NumberInt(8),
                "_id": "5d933653926ab4002589a3b6",
                "varName": "d"
              },
              {
                "varValue": NumberInt(9),
                "_id": "5d933653926ab4002589a3b5",
                "varName": "e"
              }
            ],
            "__v": NumberInt(0)
          },
          "partType": {
            "_id": "5d93390a926ab4002589a3df",
            "createdAt": "2019-10-01T11:31:22.307Z",
            "updatedAt": "2019-10-01T11:32:42.600Z",
            "partTypeName": "Part Type - bolt",
            "partTypeCode": "Part code - bolt",
            "shape": "5d933653926ab4002589a3b4",
            "partTypeCat": "5d9338b8926ab4002589a3dd",
            "material": [
              {
                "_id": "5d9332bd926ab4002589a3ae",
                "createdAt": "2019-10-01T11:04:29.180Z",
                "updatedAt": "2019-10-04T09:22:32.715Z",
                "materialName": "coper",
                "density": NumberInt(10),
                "typicalRatePerKg": NumberInt(20),
                "weightPerUnit": NumberInt(5),
                "rollingIndex": NumberInt(1),
                "cuttingIndex": NumberInt(2),
                "bendingIndex": NumberInt(3),
                "fabrictionIndex": NumberInt(4),
                "efficiency": NumberInt(70),
                "materialSubCategory": "5d9332a3926ab4002589a3ad",
                "matIndex": NumberInt(1),
                "type": "standard",
                "__v": NumberInt(0)
              }
            ],
            "extras": [
              
            ],
            "addons": [
              
            ],
            "proccessing": [
              
            ],
            "__v": NumberInt(0)
          },
          "material": {
            "_id": "5d9332bd926ab4002589a3ae",
            "createdAt": "2019-10-01T11:04:29.180Z",
            "updatedAt": "2019-10-04T09:22:32.715Z",
            "materialName": "coper",
            "density": NumberInt(10),
            "typicalRatePerKg": NumberInt(20),
            "weightPerUnit": NumberInt(5),
            "rollingIndex": NumberInt(1),
            "cuttingIndex": NumberInt(2),
            "bendingIndex": NumberInt(3),
            "fabrictionIndex": NumberInt(4),
            "efficiency": NumberInt(70),
            "materialSubCategory": "5d9332a3926ab4002589a3ad",
            "matIndex": NumberInt(1),
            "type": "standard",
            "__v": NumberInt(0)
          },
          "size": "part_cat",
          "customMaterial": "",
          "quantity": NumberInt(1),
          "variable": [
            {
              "varValue": NumberInt(2),
              "_id": "5d933653926ab4002589a3b9",
              "varName": "a"
            },
            {
              "varValue": NumberInt(4),
              "_id": "5d933653926ab4002589a3b8",
              "varName": "b"
            },
            {
              "varValue": NumberInt(5),
              "_id": "5d933653926ab4002589a3b7",
              "varName": "c"
            },
            {
              "varValue": NumberInt(8),
              "_id": "5d933653926ab4002589a3b6",
              "varName": "d"
            },
            {
              "varValue": NumberInt(9),
              "_id": "5d933653926ab4002589a3b5",
              "varName": "e"
            }
          ],
          "scaleFactor": "",
          "finalCalculation": {
            "materialPrice": NumberInt(160),
            "itemUnitPrice": 8294.400000000001,
            "totalCostForQuantity": 8294.400000000001,
            "salesMaterialPrice": 171.20000000000002,
            "salesItemUnitPrice": 8875.008000000002,
            "salesTotalCostForQuantity": 8875.008000000002
          },
          "keyValueCalculations": {
            "perimeter": NumberInt(8),
            "sheetMetalArea": NumberInt(40),
            "surfaceArea": NumberInt(15),
            "grossWeight": 51.84,
            "netWeight": NumberInt(48)
          },
          "processingCost": NumberInt(0),
          "addonCost": NumberInt(0),
          "extrasCost": NumberInt(0),
          "totalCost": 8294.400000000001,
          "processing": [
            
          ],
          "addons": [
            {
              "rate": NumberInt(40),
              "showRateFields": true,
              "showQuantityFields": true,
              "quantity": {
                "supportingVariable": {
                  "supportingVariable": "3",
                  "value": NumberInt(1),
                  "uom": "kg"
                },
                "keyValue": {
                  "keyVariable": "SMA",
                  "keyValue": NumberInt(80),
                  "actualKeyValue": NumberInt(40),
                  "uom": "kg"
                },
                "utilization": NumberInt(65),
                "contengncyOrWastage": NumberInt(10),
                "total": NumberInt(1)
              },
              "thickness": NumberInt(3),
              "length": NumberInt(5),
              "wastage": NumberInt(8),
              "formFactor": NumberInt(4),
              "sizeFactor": NumberInt(2),
              "totalWeight": NumberInt(286),
              "totalCost": NumberInt(2288),
              "weightPerUnit": NumberInt(5),
              "addonType": {
                "_id": "5d935199926ab4002589a415",
                "createdAt": "2019-10-01T13:16:09.149Z",
                "updatedAt": "2019-10-04T09:25:16.986Z",
                "showQuantityFields": true,
                "showRateFields": true,
                "allowAtAssSubAss": true,
                "addonTypeName": "addon rateON&QtyON",
                "remarks": "rk",
                "materialCat": {
                  "_id": "5d933268926ab4002589a3ac",
                  "createdAt": "2019-10-01T11:03:04.022Z",
                  "updatedAt": "2019-10-01T11:05:40.554Z",
                  "materialCatName": "copper material",
                  "materialCatIndex": NumberInt(1),
                  "subCat": [
                    {
                      "_id": "5d9332a3926ab4002589a3ad",
                      "createdAt": "2019-10-01T11:04:03.856Z",
                      "updatedAt": "2019-10-01T11:04:29.182Z",
                      "materialSubCatName": "copper material sub_cat",
                      "catId": "5d933268926ab4002589a3ac",
                      "subCatIndex": NumberInt(1),
                      "materials": [
                        "5d9332bd926ab4002589a3ae"
                      ],
                      "__v": NumberInt(0)
                    },
                    {
                      "_id": "5d933304926ab4002589a3af",
                      "createdAt": "2019-10-01T11:05:40.552Z",
                      "updatedAt": "2019-10-01T11:12:24.936Z",
                      "materialSubCatName": "copper material sub_cat2",
                      "catId": "5d933268926ab4002589a3ac",
                      "subCatIndex": NumberInt(2),
                      "materials": [
                        "5d93332e926ab4002589a3b0",
                        "5d9333e2926ab4002589a3b1",
                        "5d933493926ab4002589a3b2",
                        "5d933498926ab4002589a3b3"
                      ],
                      "__v": NumberInt(0)
                    }
                  ],
                  "__v": NumberInt(0)
                },
                "materialSubCat": {
                  "_id": "5d9332a3926ab4002589a3ad",
                  "createdAt": "2019-10-01T11:04:03.856Z",
                  "updatedAt": "2019-10-01T11:04:29.182Z",
                  "materialSubCatName": "copper material sub_cat",
                  "catId": "5d933268926ab4002589a3ac",
                  "subCatIndex": NumberInt(1),
                  "materials": [
                    "5d9332bd926ab4002589a3ae"
                  ],
                  "__v": NumberInt(0)
                },
                "addonIndex": NumberInt(1),
                "quantity": {
                  "additionalInput": "3",
                  "linkedKey": "SMA",
                  "mulFact": "2",
                  "percentageUse": NumberInt(65),
                  "additionalInputUom": {
                    "_id": "5a33a16e23996d036964ad4c",
                    "createdAt": "2017-12-15T10:18:22.373Z",
                    "updatedAt": "2017-12-15T10:18:22.373Z",
                    "uomName": "kg",
                    "__v": NumberInt(0)
                  },
                  "linkedKeyUom": {
                    "_id": "5a33a16e23996d036964ad4c",
                    "createdAt": "2017-12-15T10:18:22.373Z",
                    "updatedAt": "2017-12-15T10:18:22.373Z",
                    "uomName": "kg",
                    "__v": NumberInt(0)
                  },
                  "finalUom": null
                },
                "rate": {
                  "mulFact": "2",
                  "uom": {
                    "_id": "5a33a16e23996d036964ad4c",
                    "createdAt": "2017-12-15T10:18:22.373Z",
                    "updatedAt": "2017-12-15T10:18:22.373Z",
                    "uomName": "kg",
                    "__v": NumberInt(0)
                  }
                },
                "__v": NumberInt(0)
              },
              "addonItem": {
                "_id": "5d9332bd926ab4002589a3ae",
                "createdAt": "2019-10-01T11:04:29.180Z",
                "updatedAt": "2019-10-04T09:22:32.715Z",
                "materialName": "coper",
                "density": NumberInt(10),
                "typicalRatePerKg": NumberInt(20),
                "weightPerUnit": NumberInt(5),
                "rollingIndex": NumberInt(1),
                "cuttingIndex": NumberInt(2),
                "bendingIndex": NumberInt(3),
                "fabrictionIndex": NumberInt(4),
                "efficiency": NumberInt(70),
                "materialSubCategory": {
                  "_id": "5d9332a3926ab4002589a3ad",
                  "createdAt": "2019-10-01T11:04:03.856Z",
                  "updatedAt": "2019-10-01T11:04:29.182Z",
                  "materialSubCatName": "copper material sub_cat",
                  "catId": "5d933268926ab4002589a3ac",
                  "subCatIndex": NumberInt(1),
                  "__v": NumberInt(0),
                  "materials": [
                    "5d9332bd926ab4002589a3ae"
                  ]
                },
                "matIndex": NumberInt(1),
                "__v": NumberInt(0),
                "type": "standard"
              },
              "addonNumber": "AS5SA1PT1AD1",
              "salesTotalCost": 2542.222222222222
            }
          ],
          "extras": [
            
          ],
          "partUpdateStatus": true,
          "shape": {
            "_id": "5d933653926ab4002589a3b4",
            "createdAt": "2019-10-01T11:19:47.471Z",
            "updatedAt": "2019-10-17T10:30:17.287Z",
            "shapeName": "polygon rt",
            "namingConvention": "polygon rtN",
            "shapeIndex": NumberInt(1),
            "partFormulae": {
              "perimeter": "a*b",
              "sheetMetalArea": "c*d",
              "surfaceArea": "a+b+c+d/2",
              "netWeight": "(a*b)+(c*d)",
              "grossWeight": "((a*b)+(c*d))*((wtg+100)/100)"
            },
            "wastage": "8",
            "length": NumberInt(5),
            "thickness": NumberInt(3),
            "formFactor": "4",
            "sizeFactor": "2",
            "variable": [
              {
                "_id": "5da842b9ed5e430118c59af2",
                "varName": "a"
              },
              {
                "_id": "5da842b9ed5e430118c59af1",
                "varName": "b"
              },
              {
                "_id": "5da842b9ed5e430118c59af0",
                "varName": "c"
              },
              {
                "_id": "5da842b9ed5e430118c59aef",
                "varName": "d"
              },
              {
                "_id": "5da842b9ed5e430118c59aee",
                "varName": "e"
              }
            ],
            "icon": {
              "fileId": "1569928706824---Screenshot_from_2019-08-26_13-34-36.png",
              "fileName": "Screenshot_from_2019-08-26_13-34-36.png"
            },
            "type": "2d",
            "__v": NumberInt(0),
            "image": {
              "fileId": "1571308212544---Screenshot_from_2019-10-09_13-17-38.png",
              "fileName": "Screenshot_from_2019-10-09_13-17-38.png"
            }
          },
          "formFactor": NumberInt(4),
          "length": NumberInt(5),
          "sizeFactor": NumberInt(2),
          "thickness": NumberInt(3),
          "wastage": NumberInt(8),
          "partValidationStatus": true,
          "salesProcessingCost": NumberInt(0),
          "salesAddonCost": NumberInt(0),
          "addonWeight": NumberInt(0),
          "salesExtrasCost": NumberInt(0),
          "salesTotalCost": 8875.008000000002
        }
      ],
      "processing": [
        
      ],
      "addons": [
        {
          "rate": NumberInt(40),
          "showRateFields": true,
          "showQuantityFields": true,
          "quantity": {
            "supportingVariable": {
              "supportingVariable": "3",
              "value": NumberInt(1),
              "uom": "kg"
            },
            "keyValue": {
              "keyVariable": "SMA",
              "keyValue": NumberInt(80),
              "actualKeyValue": NumberInt(40),
              "uom": "kg"
            },
            "utilization": NumberInt(65),
            "contengncyOrWastage": NumberInt(10),
            "total": NumberInt(1)
          },
          "totalWeight": NumberInt(286),
          "totalCost": NumberInt(2288),
          "weightPerUnit": NumberInt(5),
          "addonType": {
            "_id": "5d935199926ab4002589a415",
            "createdAt": "2019-10-01T13:16:09.149Z",
            "updatedAt": "2019-10-04T09:25:16.986Z",
            "showQuantityFields": true,
            "showRateFields": true,
            "allowAtAssSubAss": true,
            "addonTypeName": "addon rateON&QtyON",
            "remarks": "rk",
            "materialCat": {
              "_id": "5d933268926ab4002589a3ac",
              "createdAt": "2019-10-01T11:03:04.022Z",
              "updatedAt": "2019-10-01T11:05:40.554Z",
              "materialCatName": "copper material",
              "materialCatIndex": NumberInt(1),
              "subCat": [
                {
                  "_id": "5d9332a3926ab4002589a3ad",
                  "createdAt": "2019-10-01T11:04:03.856Z",
                  "updatedAt": "2019-10-01T11:04:29.182Z",
                  "materialSubCatName": "copper material sub_cat",
                  "catId": "5d933268926ab4002589a3ac",
                  "subCatIndex": NumberInt(1),
                  "materials": [
                    "5d9332bd926ab4002589a3ae"
                  ],
                  "__v": NumberInt(0)
                },
                {
                  "_id": "5d933304926ab4002589a3af",
                  "createdAt": "2019-10-01T11:05:40.552Z",
                  "updatedAt": "2019-10-01T11:12:24.936Z",
                  "materialSubCatName": "copper material sub_cat2",
                  "catId": "5d933268926ab4002589a3ac",
                  "subCatIndex": NumberInt(2),
                  "materials": [
                    "5d93332e926ab4002589a3b0",
                    "5d9333e2926ab4002589a3b1",
                    "5d933493926ab4002589a3b2",
                    "5d933498926ab4002589a3b3"
                  ],
                  "__v": NumberInt(0)
                }
              ],
              "__v": NumberInt(0)
            },
            "materialSubCat": {
              "_id": "5d9332a3926ab4002589a3ad",
              "createdAt": "2019-10-01T11:04:03.856Z",
              "updatedAt": "2019-10-01T11:04:29.182Z",
              "materialSubCatName": "copper material sub_cat",
              "catId": "5d933268926ab4002589a3ac",
              "subCatIndex": NumberInt(1),
              "materials": [
                "5d9332bd926ab4002589a3ae"
              ],
              "__v": NumberInt(0)
            },
            "addonIndex": NumberInt(1),
            "quantity": {
              "additionalInput": "3",
              "linkedKey": "SMA",
              "mulFact": "2",
              "percentageUse": NumberInt(65),
              "additionalInputUom": {
                "_id": "5a33a16e23996d036964ad4c",
                "createdAt": "2017-12-15T10:18:22.373Z",
                "updatedAt": "2017-12-15T10:18:22.373Z",
                "uomName": "kg",
                "__v": NumberInt(0)
              },
              "linkedKeyUom": {
                "_id": "5a33a16e23996d036964ad4c",
                "createdAt": "2017-12-15T10:18:22.373Z",
                "updatedAt": "2017-12-15T10:18:22.373Z",
                "uomName": "kg",
                "__v": NumberInt(0)
              },
              "finalUom": null
            },
            "rate": {
              "mulFact": "2",
              "uom": {
                "_id": "5a33a16e23996d036964ad4c",
                "createdAt": "2017-12-15T10:18:22.373Z",
                "updatedAt": "2017-12-15T10:18:22.373Z",
                "uomName": "kg",
                "__v": NumberInt(0)
              }
            },
            "__v": NumberInt(0)
          },
          "addonItem": {
            "_id": "5d9332bd926ab4002589a3ae",
            "createdAt": "2019-10-01T11:04:29.180Z",
            "updatedAt": "2019-10-04T09:22:32.715Z",
            "materialName": "coper",
            "density": NumberInt(10),
            "typicalRatePerKg": NumberInt(20),
            "weightPerUnit": NumberInt(5),
            "rollingIndex": NumberInt(1),
            "cuttingIndex": NumberInt(2),
            "bendingIndex": NumberInt(3),
            "fabrictionIndex": NumberInt(4),
            "efficiency": NumberInt(70),
            "materialSubCategory": {
              "_id": "5d9332a3926ab4002589a3ad",
              "createdAt": "2019-10-01T11:04:03.856Z",
              "updatedAt": "2019-10-01T11:04:29.182Z",
              "materialSubCatName": "copper material sub_cat",
              "catId": "5d933268926ab4002589a3ac",
              "subCatIndex": NumberInt(1),
              "__v": NumberInt(0),
              "materials": [
                "5d9332bd926ab4002589a3ae"
              ]
            },
            "matIndex": NumberInt(1),
            "__v": NumberInt(0),
            "type": "standard"
          },
          "addonNumber": "AS5SA1AD1",
          "salesTotalCost": 2542.222222222222
        }
      ],
      "extras": [
        
      ],
      "addonWeight": NumberInt(0),
      "totalNetWeight": NumberInt(48),
      "salesMaterialCost": 8875.008000000002,
      "salesProcessingCost": NumberInt(0),
      "salesAddonCost": NumberInt(0),
      "salesExtrasCost": NumberInt(0),
      "salesTotalCost": 8875.008000000002
    }
  ],
  "estimateAttachment": [
    
  ],
  "keyValueCalculations": {
    "perimeter": NumberInt(8),
    "sheetMetalArea": NumberInt(40),
    "surfaceArea": NumberInt(15),
    "grossWeight": 51.84,
    "netWeight": NumberInt(48)
  },
  "remarks": [
    
  ],
  "__v": NumberInt(0),
  "commission": NumberInt(10),
  "negotiation": NumberInt(15),
  "other": NumberInt(2),
  "scaleFactors": {
    "factor": "budgetory",
    "low": NumberInt(5),
    "medium": NumberInt(12),
    "high": NumberInt(7),
    "budgetory": NumberInt(8)
  }
}
