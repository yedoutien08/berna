import { ActivatedRoute, Router } from "@angular/router";

export class Utils {

    public static _paginationDatatable() {
        setTimeout(function() {
            $(function(){
              $('#example').DataTable();
          });
        },200)
    }

}