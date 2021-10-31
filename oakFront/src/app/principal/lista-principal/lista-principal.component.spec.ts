import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaPrincipalComponent } from './lista-principal.component';

describe('ListaPrincipalComponent', () => {
  let component: ListaPrincipalComponent;
  let fixture: ComponentFixture<ListaPrincipalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaPrincipalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaPrincipalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
