import {Project} from './project';

export class Board {
  id: number;
  name: string;
  kanbanId: number;
  projectId: number;
  projectName: string;
  project?: Project;

}
