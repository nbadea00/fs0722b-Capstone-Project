import { Injectable } from '@angular/core';
import {
  BreakpointObserver,
  BreakpointState,
  Breakpoints,
} from '@angular/cdk/layout';
import { BehaviorSubject, Observable } from 'rxjs';
import { switchMap, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class FlexLayoutService {
  displayNameMap = new Map([
    [Breakpoints.XSmall, 'xs'],
    [Breakpoints.Small, 'sd'],
    [Breakpoints.Medium, 'md'],
    [Breakpoints.Large, 'ld'],
    [Breakpoints.XLarge, 'xl'],
  ]);
currentScreenSize: string = 'default';

arr : string[] = ['xs', 'sd', 'md', 'ld', 'xl'];

  constructor(private breakpointObserver: BreakpointObserver) {
    breakpointObserver
      .observe([
        Breakpoints.XSmall,
        Breakpoints.Small,
        Breakpoints.Medium,
        Breakpoints.Large,
        Breakpoints.XLarge,
      ])
      .pipe(
        tap((result) => {
          for (const query of Object.keys(result.breakpoints))
            if (result.breakpoints[query])
              this.currentScreenSize = this.displayNameMap.get(query) ?? 'default';
        })
      )
      .subscribe();
  }

  flex(args: Map<string, string>): string {
    for(let i = this.arr.indexOf(this.currentScreenSize); i >= 0; i--) if(args.has(this.arr[i])) return args.get(this.arr[i])!;
    return args.get('default')!;
  }
}
