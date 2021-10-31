import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoriasAdminComponent } from './categorias-admin.component';

describe('CategoriasAdminComponent', () => {
  let component: CategoriasAdminComponent;
  let fixture: ComponentFixture<CategoriasAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CategoriasAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoriasAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
