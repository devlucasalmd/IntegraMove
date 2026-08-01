import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  standalone: true,
  selector: 'app-header-search',
  imports: [MatIconModule, MatInputModule, MatFormFieldModule],
  templateUrl: './header-search.component.html',
  styleUrls: ['./header-search.component.css'],
})
export class HeaderSearchComponent {}
