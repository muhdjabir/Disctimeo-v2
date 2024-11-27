import { Member } from "@/types/club"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"

interface MemberListProps {
    members: Member[]
}

export function MemberList({ members }: MemberListProps) {
    return (
        <div className="space-y-4">
            <h3 className="text-lg font-semibold">Club Members</h3>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                {members.map((member) => (
                    <div key={member.id} className="flex items-center space-x-4 p-2 rounded-lg bg-muted">
                        <Avatar>
                            <AvatarImage src={member.avatarUrl} alt={member.name} />
                            <AvatarFallback>{member.name.charAt(0)}</AvatarFallback>
                        </Avatar>
                        <div>
                            <p className="font-medium">{member.name}</p>
                            <p className="text-sm text-muted-foreground">{member.role}</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
}

