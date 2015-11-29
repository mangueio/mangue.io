'use strict';

/* Controllers */

  // bootstrap controller
  app.controller('UsersCtrl', ['$scope', '$localStorage', '$mdDialog', function($scope, $localStorage, $mdDialog) {
  	var originatorEv;
  	$scope.openMenu = function($mdOpenMenu, ev) {
  		originatorEv = ev;
  		$mdOpenMenu(ev);
  	};

  	$scope.$watch('collections', function(newVal, oldVal){
    if(newVal)//
    	collectionsLoaded();
})

  	$scope.filterOptions = {
  		filterText: "",
  		useExternalFilter: true
  	}; 
  	$scope.totalServerItems = 0;
  	$scope.pagingOptions = {
  		pageSizes: [30, 50, 100],
  		pageSize: 50,
  		currentPage: 1
  	};  
  	$scope.setPagingData = function(data, page, pageSize){  
  		var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
  		$scope.pageData = pagedData;
  		$scope.totalServerItems = data.length;
  		if (!$scope.$$phase) {
  			$scope.$apply();
  		}
  	};
  	$scope.getPagedDataAsync = function (pageSize, page, searchText) {
  		setTimeout(function () {
  			var data;
  			if (searchText) {
              // var ft = searchText.toLowerCase();
              // $http.get('js/modules/ng-grid/largeLoad.json').success(function (largeLoad) {    
              //     data = largeLoad.filter(function(item) {
              //         return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
              //     });
              //     $scope.setPagingData(data,page,pageSize);
              // }); 
  	} else {
              // $http.get('js/modules/ng-grid/largeLoad.json').success(function (largeLoad) {
              //     $scope.setPagingData(largeLoad,page,pageSize);
              // });
  		PulseHub.getAppData(app.id, currentCollection.name, "", 1, Math.pow(2, 30), "_id,desc").success(function(response){
                //console.log($scope.data);
                $scope.data = response;
                $scope.setPagingData($scope.data,page,pageSize);
            }).finally(function(){
            	$scope.app.loading = false;
            })
        }
    }, 100);
};

$scope.$watch('pagingOptions', function (newVal, oldVal) {
	if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
		$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
	}
}, true);
$scope.$watch('filterOptions', function (newVal, oldVal) {
	if (newVal !== oldVal) {
		$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
	}
}, true);

$scope.refreshData = function(){
	$scope.app.loading = true;
	$scope.pageData = []
	$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
}

$scope.externalFuncions = {}

$scope.gridOptions = {
	data: [
	{
		"name": "Ethel Price",
		"gender": "female",
		"company": "Enersol"
	},
	{
		"name": "Claudine Neal",
		"gender": "female",
		"company": "Sealoud"
	},
	{
		"name": "Beryl Rice",
		"gender": "female",
		"company": "Velity"
	},
	{
		"name": "Wilder Gonzales",
		"gender": "male",
		"company": "Geekko"
	},
	{
		"name": "Georgina Schultz",
		"gender": "female",
		"company": "Suretech"
	},
	{
		"name": "Carroll Buchanan",
		"gender": "male",
		"company": "Ecosys"
	},
	{
		"name": "Valarie Atkinson",
		"gender": "female",
		"company": "Hopeli"
	},
	{
		"name": "Schroeder Mathews",
		"gender": "male",
		"company": "Polarium"
	},
	{
		"name": "Lynda Mendoza",
		"gender": "female",
		"company": "Dogspa"
	},
	{
		"name": "Sarah Massey",
		"gender": "female",
		"company": "Bisba"
	},
	{
		"name": "Robles Boyle",
		"gender": "male",
		"company": "Comtract"
	},
	{
		"name": "Evans Hickman",
		"gender": "male",
		"company": "Parleynet"
	},
	{
		"name": "Dawson Barber",
		"gender": "male",
		"company": "Dymi"
	},
	{
		"name": "Bruce Strong",
		"gender": "male",
		"company": "Xyqag"
	},
	{
		"name": "Nellie Whitfield",
		"gender": "female",
		"company": "Exospace"
	},
	{
		"name": "Jackson Macias",
		"gender": "male",
		"company": "Aquamate"
	},
	{
		"name": "Pena Pena",
		"gender": "male",
		"company": "Quarx"
	},
	{
		"name": "Lelia Gates",
		"gender": "female",
		"company": "Proxsoft"
	},
	{
		"name": "Letitia Vasquez",
		"gender": "female",
		"company": "Slumberia"
	},
	{
		"name": "Trevino Moreno",
		"gender": "male",
		"company": "Conjurica"
	}
	]
	,
	primaryKey: 'id',
	data: 'pageData',
	enablePaging: true,
	totalServerItems: 'totalServerItems',
	pagingOptions: $scope.pagingOptions,
	filterOptions: $scope.filterOptions,
	enableRowSelection: true,
	enableSelectAll: true,
	selectionRowHeaderWidth: 35,
	rowEditWaitInterval: 100,
	multiSelect: true,
	enableCellEdit: true,
	columnDefs:[{
		width: 150,
		field: 'id',
		displayName: 'id',
		enableCellEdit: false
	}, {
		field: 'createdAt',
		displayName: 'createdAt',
		width: 150,
		enableCellEdit: false,
		cellFilter: "date:'yyyy-MM-ddTHH:mm:ss.sss'"
	}, {
		field: 'acl',
		displayName: 'ACL',
		width: 150,
		enableCellEdit: false,
		cellTemplate:
		'<div class="ui-grid-cell-contents">'+
		'<button ng-click="getExternalScopes().openACLEditor(col.field, row.entity[col.field], row.entity)" class="code-editor-btn btn btn-dark no-borders no-radius btn-xs text-md btn-default">'+
		'<i class="icon-key fa-fw"></i>'+
		'</button>'+
		'<div class="ui-grid-wrapped-data">{{row.entity[col.field]}}</div>'+
		'</div>'
    /*}, {
      field: 'updatedAt',
      enableCellEdit: false,
      cellFilter: "date:'yyyy-MM-ddTHH:mm:ss.sss'"*/
  }]
};


	$scope.columns = $scope.gridOptions.columnDefs

  var collectionLoaded = function(){
    currentCollection && currentCollection.columns && currentCollection.columns.forEach(function(column, index){
        $scope.gridOptions.columnDefs.push({
        field: column,
        displayName: column,
        cellTemplate:
        '<div class="ui-grid-cell-contents">'+
          '<button ng-click="getExternalScopes().openDocumentEditor(col.field, row.entity[col.field], row.entity)" class="code-editor-btn btn btn-dark no-borders no-radius btn-xs text-md btn-default">'+
          '<i class="fa-code fa fa-fw"></i>'+
          '</button>'+
          '<div class="ui-grid-wrapped-data">{{row.entity[col.field]}}</div>'+
        '</div>',
        width: 150, menuItems: [{
          title: 'Delete column',
          icon: 'fa fa-fw fa-trash-o',
          action: function() {
            $scope.openModal('delete_collection_column.html', null, column)
          }
        }]
      });
    });
  }

  $scope.toggleColumn = function(column, allNone){
    if(allNone === "all"){
      $scope.columns.forEach(function(col, index){
        col.visible = true;
      });
    }else if(allNone === "none"){
      $scope.columns.forEach(function(col, index){
        col.visible = false;
      });
    }else{
      if(typeof column.visible === 'undefined')
        column.visible = true;  
      column.visible = !column.visible
    }

    $scope.gridApi.core.notifyDataChange($scope.gridApi.grid, uiGridConstants.dataChange.COLUMN);
  }

  $scope.createColumn =  function(column){
    if(column)
      PulseHub.postAppCollectionColumn(app.id, currentCollection.name, column).success(function(){
        if($scope.gridOptions && $scope.gridOptions.columnDefs){
            $scope.gridOptions.columnDefs.push({
            field: column,
            displayName: column,
            cellTemplate:
            '<div class="ui-grid-cell-contents">'+
              '<button ng-click="getExternalScopes().openDocumentEditor(col.field, row.entity[col.field], row.entity)" class="code-editor-btn btn btn-dark no-borders no-radius btn-xs text-md btn-default">'+
              '<i class="fa-code fa fa-fw"></i>'+
              '</button>'+
              '<div class="ui-grid-wrapped-data">{{row.entity[col.field]}}</div>'+
            '</div>',
            width: 150, menuItems: [{
              title: 'Delete column',
              icon: 'fa fa-fw fa-trash-o',
              action: function() {
                $scope.openModal('delete_collection_column.html', null, column)
              },
              visible: true
            }]
          });
          toastr.success("Column added.");
          $scope.getCollections();
        }
      }).error(function(){
        toastr.error("Error adding column.");
      }).finally(function(){$scope.cancelModal();})
  }

  $scope.deleteCollectionColumn = function(){
    var columnName = $scope.modalObject;
    PulseHub.deleteAppCollectionColumn(app.id, currentCollection.name, columnName).success(function(){
      for (var i = $scope.gridOptions.columnDefs.length - 1; i >= 0; i--) {
        if($scope.gridOptions.columnDefs[i].field === columnName)
          $scope.gridOptions.columnDefs.splice(i, 1);
      };
      toastr.success("Column has been removed.");
      $scope.getCollections();
    }).error(function(){toastr.error("Error deleting column.");})
    .finally(function(){$scope.cancelModal();})
  }

  if(app.collectionColumns != null && app.collectionColumns.length){
    app.collectionColumns.forEach(function(column, index){
      $scope.gridOptions.columnDefs.push({field: column, width: 150});
    });
  }

  $scope.gridOptions.onRegisterApi = function(gridApi){
    //set gridApi on scope
    $scope.gridApi = gridApi;
    gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef, newValue, oldValue){
    });
  }

  $scope.createRow = function(){
    var newRow = {};
    if($scope.pageData && $scope.pageData[0] != null && $scope.pageData[0].id == null){
      toastr.info("Use the empty row already added.")
      return;
    }
    toastr.info("Empty row added.")
    $scope.pageData.unshift(newRow)
  }

  $scope.deleteRows = function(){
    if($scope.rowsSelected){
      var ids = [];
      $scope.rowsSelected.forEach(function(row, index){
        ids.push(row.entity.id);
      });
      $scope.app.loading = true;
      PulseHub.deleteAppData(app.id, currentCollection.name, ids).success(function(){
        for (var i = $scope.pageData.length - 1; i >= 0; i--) {
          for (var j = $scope.rowsSelected.length - 1; j >= 0; j--) {
            if($scope.rowsSelected[j] && $scope.pageData[i] && $scope.pageData[i].id == $scope.rowsSelected[j].entity.id )
              $scope.rowsSelected.splice(j, 1)
          };
          ids.forEach(function(id, index){
            if($scope.pageData[i] && $scope.pageData[i].id == id)
              $scope.pageData.splice(i, 1)
          });
        };
      }).finally(function(){
        $scope.cancelModal();
        $scope.app.loading = false;
      })
    }
  }

  $scope.saveRow = function(rowEntity, callback) {
    if(rowEntity && rowEntity.id){
      // create a fake promise - normally you'd use the promise returned by $http or $resource
      var promise = $q.defer();
      $scope.gridApi.rowEdit.setSavePromise( $scope.gridApi.grid, rowEntity, promise.promise );
      $scope.app.loading = true;
      PulseHub.putAppData(app.id, currentCollection.name, rowEntity).success(function(){
        promise.resolve();
        toastr.success("Data has been updated.")
      })
      .error(function(){
        toastr.error(response.message);
        promise.reject();
        promise.reject();
      }).finally(function(){
        $scope.app.loading = false;
        if(callback)
          callback();
      })
    }else{
      var promise = $q.defer();
      $scope.gridApi.rowEdit.setSavePromise( $scope.gridApi.grid, rowEntity, promise.promise );
      PulseHub.postAppData(app.id, currentCollection.name, rowEntity).success(function(data){
        for (var k in data){
          if (data.hasOwnProperty(k)) {
            rowEntity[k] = data[k];
          }
        }
        promise.resolve();
        toastr.success("Entry created.")
      }).error(function(){
        toastr.error(response.message);
        promise.reject();
      }).finally(function(){
      });
    }
  }; 
 
  $scope.gridOptions.onRegisterApi = function(gridApi){
    //set gridApi on scope
    // console.log(gridApi);
    $scope.gridApi = gridApi;
    gridApi.rowEdit.on.saveRow($scope, $scope.saveRow);
    gridApi.selection.on.rowSelectionChanged($scope,function(row){
      rowSelection(row)
    });
    gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
      rows.forEach(function(row, index){
        rowSelection(row);
      });
    });
  };

  $scope.settingsTab = 'general';
  $scope.setSettingsTab = function(tab){
    $scope.settingsTab = tab;
  }

  $scope.rowsSelected = [];
  var rowSelection = function (row){
    var contains = false;
    for (var i = $scope.rowsSelected.length - 1; i >= 0; i--) {
      if(row.entity.id == $scope.rowsSelected[i].entity.id){
        contains = true;
        if(!row.isSelected)
          $scope.rowsSelected.splice(i, 1)
      }
    };
    if(!contains && row.isSelected){
      $scope.rowsSelected.push({
        entity: row.entity,
        uid: row.uid
      });
    }
  }

  $scope.editor = null;
  $scope.loadEditor = function(editor){
    $scope.editor = editor;
  }

  $scope.externalFuncions = {}
  $scope.externalFuncions.openDocumentEditor = function(fieldName, fieldData, rowEntity){
  $scope.editorValue = null;
    $scope.openCodeModal('document_editor.html', 'lg')
    $interval(function(){
      $scope.editorValue = JSON.stringify(fieldData, null, 2);
      $scope.editor.focus();
    }, 300, 1)
    $scope.externalFuncions.fieldName = fieldName
    $scope.externalFuncions.fieldData = fieldData
    $scope.externalFuncions.rowEntity = rowEntity
  }

  $scope.aclTab = 'visual';
  $scope.setAclTab = function(tab){
    $scope.aclTab = tab;
  }
  
  $scope.externalFuncions.openACLEditor = function(fieldName, fieldData, rowEntity){
    $scope.editorValue = null;
    $scope.tempAcl = angular.copy(rowEntity.acl);
    $scope.openCodeModal('acl_editor.html', 'lg')
    $scope.externalFuncions.fieldName = fieldName
    $scope.externalFuncions.fieldData = fieldData
    $scope.externalFuncions.rowEntity = rowEntity
    $interval(function(){
      $scope.editorValue = JSON.stringify(angular.copy(rowEntity.acl), null, 2);
      $scope.editor.on('blur', checkAclCodeEditorAndUpdate)
    }, 300, 1)
  }

  $scope.updateAclPermission = function(){
    updateAclEditor();
  }

  function checkAclCodeEditorAndUpdate(){
    window.console && console.info('bluring acl code editor.');
    try{
      var acl = JSON.parse($scope.editor.getValue().replace(/(?:\r\n|\r|\n)/g,''));
      $scope.tempAcl = acl
    }catch(e){
      toastr.error("Syntax error!")
    }
  }

  function updateAclEditor(){
    if($scope.tempAcl.accessList && $scope.tempAcl.accessList.length > 0){
      $scope.tempAcl.accessList.forEach(function(element, index){
        delete element['$$hashKey']
      });
    }
    var json = JSON.stringify($scope.tempAcl);
    $scope.editor.setValue(json);
    $scope.indent();
  }

  $scope.addAccess = function(aclAccess){
    if(!aclAccess || !aclAccess.userInfo || !aclAccess.permission){
      toastr.error("Invalid ACL access."); return;
    }

    var query = {
      '$or': [{'_id': aclAccess.userInfo}, {'email': aclAccess.userInfo}, {'username': aclAccess.userInfo}]
    }

    PulseHub.getAppUsers($scope.currentApp.id, JSON.stringify(query), 1, 1, 'id,asc').success(function(response){
      if(response && response[0]){
        var user = response[0];
        aclAccess.userId = user.id;
        var accessList = addToAccessList($scope.tempAcl, aclAccess);
        if(!accessList){
          toastr.info("User has already been added.")
          return;
        }
        $scope.tempAcl.accessList = accessList;
        updateAclEditor();
      }else{
        toastr.error("No user found.")  
      }
    }).error(function(){
      toastr.error("Error finding user.")
    })
  }

  function addToAccessList(acl, aclAccess){
    if(!acl.accessList)
      acl.accessList = [];

    var add = true;
    acl.accessList.forEach(function(access, index){
      if(access.userId === aclAccess.userId)
        add = false;
    });

    if(add){
      acl.accessList.unshift({userId: aclAccess.userId, permission: aclAccess.permission})
    }else{
      return null;
    }

    if(acl.accessList.length > 0)
      return acl.accessList;
  }

  $scope.removeAccess = function(userId){
    removeFromAccessList($scope.tempAcl, userId);
    updateAclEditor();
  }

  function removeFromAccessList(acl, userId){
    for (var i = acl.accessList.length - 1; i >= 0; i--) {
      if(acl.accessList[i].userId === userId)
        acl.accessList.splice(i, 1)
    };
    if(!acl.accessList || acl.accessList.length == 0)
      delete acl['accessList'];
  }

  $scope.saveEditorData = function(){
    var data = null;
    try{
      data = JSON.parse($scope.editor.getValue());
      $scope.externalFuncions.rowEntity[$scope.externalFuncions.fieldName] = data
      $scope.saveRow($scope.externalFuncions.rowEntity, function(){

      })
    }catch(e){
      toastr.error("Could not parse data.")
    }
  }

  $scope.indent = function(){
    try{
      var data = JSON.stringify(JSON.parse($scope.editor.getValue().replace(/(?:\r\n|\r|\n)/g,'')), null, 2)
      $scope.editor.setValue(data)
    }catch(e){
      toastr.error("Syntax error!")
    }
  }

  $scope.editorThemeList = [{name: 'chrome'}, {name: 'clouds'}, {name: 'crimson_editor'}, {name: 'dawn'}, {name: 'dreamweaver'}, {name: 'eclipse'}, {name: 'github'}, {name: 'solarized_light'}, {name: 'textmate'}, {name: 'tomorrow'}, {name: 'xcode'}, {name: 'kuroir'}, {name: 'katzenmilch'}, {name: 'ambiance'}, {name: 'chaos'}, {name: 'clouds_midnight'}, {name: 'cobalt'}, {name: 'idle_fingers'}, {name: 'kr_theme'}, {name: 'merbivore'}, {name: 'merbivore_soft'}, {name: 'mono_industrial'}, {name: 'monokai'}, {name: 'pastel_on_dark'}, {name: 'solarized_dark'}, {name: 'terminal'}, {name: 'tomorrow_night'}, {name: 'tomorrow_night_blue'}, {name: 'tomorrow_night_bright'}, {name: 'tomorrow_night_eighties'}, {name: 'twilight'}, {name: 'vibrant_ink'}]

  if(angular.isDefined($localStorage.editorTheme)) {
    for (var i = $scope.editorThemeList.length - 1; i >= 0; i--) {
      if($scope.editorThemeList[i].name === $localStorage.editorTheme.name){
        $scope.editorTheme = $scope.editorThemeList[i]; break;
      }
    };
    if(!$scope.editorTheme)
      $localStorage.editorTheme = $scope.editorTheme = $scope.editorThemeList[2];
  }else {
    $localStorage.editorTheme = $scope.editorTheme = $scope.editorThemeList[2];
  }
  $scope.changeEditor = function(editorTheme){
    $localStorage.editorTheme = $scope.editorTheme = editorTheme
  };

  $scope.openCodeModal = function(templateId, size, object){
    $scope.modalObject = object;
    $scope.modalInstance = $modal.open({
        windowClass: "modal fade in",
        templateUrl: templateId, // the id of the <script> template
        size: size,
        scope: $scope, // pass the current scope. no need for a new controller
      });

    closeDropdown();
  }

  $scope.deleteDataLock = "";
  $scope.deleteCurrentCollectionData = function(){
    PulseHub.deleteAppCollectionData(app.id, currentCollection.name).success(function(response){
      $scope.refreshData();
      if(response === "true")
        toastr.success("All data has been deleted.");
      if(response === "false")
        toastr.info("No data was deleted.");
    })
  }

  $scope.deleteCollectionLock = "";
  $scope.deleteCurrentCollection = function(){
    PulseHub.deleteAppCollection(app.id, currentCollection.name).success(function(){
      $scope.cancelModal();
      toastr.success("Collection has been deleted.");
      if($scope.collections)
        $scope.collections=null
      if($scope.currentApp && $scope.currentApp.currentCollection)
        $scope.currentApp.currentCollection=null
      $state.go("app.collections", {appNamespace: $scope.currentApp.namespace}, {reload: true, inherit: false, notify: true})
    }).error(function(){
      toastr.error("Error deleting collection.");
    })
  }


}]); 