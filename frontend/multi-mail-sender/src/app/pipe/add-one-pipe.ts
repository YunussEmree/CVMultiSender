import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'addOne',
  standalone: true
})
export class AddOnePipe implements PipeTransform {
  transform(value: number): number {
    return value + 1;
  }
}
