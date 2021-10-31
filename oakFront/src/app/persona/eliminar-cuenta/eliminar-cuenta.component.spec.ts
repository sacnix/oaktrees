import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EliminarCuentaComponent } from './eliminar-cuenta.component';

describe('EliminarCuentaComponent', () => {
  let component: EliminarCuentaComponent;
  let fixture: ComponentFixture<EliminarCuentaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EliminarCuentaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EliminarCuentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
