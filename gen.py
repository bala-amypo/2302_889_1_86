import os
import re

# Path to your Java project
PROJECT_DIR = '/home/coder/Workspace/demo/src/main/java/com/example/demo'

# Step 1: Find duplicate class definitions
def find_duplicate_classes():
    class_map = {}
    for root, dirs, files in os.walk(PROJECT_DIR):
        for file in files:
            if file.endswith('.java'):
                path = os.path.join(root, file)
                with open(path, 'r') as f:
                    content = f.read()
                    match = re.search(r'public\s+class\s+(\w+)', content)
                    if match:
                        class_name = match.group(1)
                        if class_name in class_map:
                            print(f'Duplicate class found: {class_name} in {path} and {class_map[class_name]}')
                        else:
                            class_map[class_name] = path

# Step 2: Automatically generate missing getters/setters for entities
def generate_getters_setters():
    for root, dirs, files in os.walk(PROJECT_DIR):
        for file in files:
            if file.endswith('.java'):
                path = os.path.join(root, file)
                with open(path, 'r') as f:
                    lines = f.readlines()
                updated_lines = []
                fields = []
                in_class = False
                class_name = ''
                for line in lines:
                    updated_lines.append(line)
                    class_match = re.search(r'public\s+class\s+(\w+)', line)
                    if class_match:
                        in_class = True
                        class_name = class_match.group(1)
                    if in_class:
                        field_match = re.search(r'private\s+(\w+)\s+(\w+);', line)
                        if field_match:
                            f_type, f_name = field_match.groups()
                            fields.append((f_type, f_name))
                # Add getters/setters at the end of class
                for f_type, f_name in fields:
                    getter = f'    public {f_type} get{f_name.capitalize()}() {{ return {f_name}; }}\n'
                    setter = f'    public void set{f_name.capitalize()}({f_type} {f_name}) {{ this.{f_name} = {f_name}; }}\n'
                    updated_lines.append(getter)
                    updated_lines.append(setter)
                # Write back
                with open(path, 'w') as f:
                    f.writelines(updated_lines)

if __name__ == '__main__':
    print('Scanning for duplicate classes...')
    find_duplicate_classes()
    print('Generating getters and setters for entities...')
    generate_getters_setters()
    print('Done.')